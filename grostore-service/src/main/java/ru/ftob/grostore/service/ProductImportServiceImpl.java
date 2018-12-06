package ru.ftob.grostore.service;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.model.product.ProductImport;
import ru.ftob.grostore.model.productlist.Category;
import ru.ftob.grostore.persistence.ProductImportRepository;
import ru.ftob.grostore.service.mapper.XlsToProductMapper;
import ru.ftob.grostore.service.product.ProductService;
import ru.ftob.grostore.service.productlist.CategoryService;
import ru.ftob.grostore.service.util.XlsHandlerUtil;
import ru.ftob.grostore.service.util.exception.ConfigurationException;
import ru.ftob.grostore.service.util.exception.NotFoundException;
import ru.ftob.grostore.service.xlsto.XlsProduct;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static ru.ftob.grostore.service.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductImportServiceImpl implements ProductImportService {

    private final ProductImportRepository productImportRepository;

    private final ProductService productService;

    private final CategoryService categoryService;

    private final XlsToProductMapper xlsToProductMapper;

    @Autowired
    public ProductImportServiceImpl(ProductImportRepository productImportRepository,
                                    ProductService productService,
                                    CategoryService categoryService,
                                    XlsToProductMapper xlsToProductMapper) {
        this.productImportRepository = productImportRepository;
        this.productService = productService;
        this.categoryService = categoryService;
        this.xlsToProductMapper = xlsToProductMapper;
    }

    @Override
    public ProductImport create(ProductImport productImport) {
        Assert.notNull(productImport, "Product Import must not be null");
        return productImportRepository.save(productImport);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(productImportRepository.delete(id), id);
    }

    @Override
    public ProductImport get(int id) throws NotFoundException {
        ProductImport productImport = checkNotFoundWithId(productImportRepository.get(id), id);
        try {
            productImport.setRaw(XlsHandlerUtil.getRaw(new File(productImport.getFile())));
        } catch (IOException e) {
            List<List<String>> raw = new ArrayList<>();
            raw.add(new ArrayList<>());
            raw.get(0).add(e.getMessage());
            productImport.setRaw(raw);
        }
        return productImport;
    }

    @Override
    public void update(ProductImport productImport) {
        Assert.notNull(productImport, "Product Import must not be null");
        Assert.notNull(productImport.getFields(), "Product Import fields must not be null");
        Assert.notNull(productImport.getFile(), "Product Import file must not be null");
        try {
            XlsHandlerUtil.addFieldsToHeader(new File(productImport.getFile()), productImport.getFields());
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkNotFoundWithId(productImportRepository.save(productImport), productImport.getId());
    }

    @Override
    public List<ProductImport> getAll() {
        return productImportRepository.getAll();
    }

    @Override
    public ProductImport getWithProducts(int id) {
        return checkNotFoundWithId(productImportRepository.getWithProducts(id), id);
    }

    @Override
    public ProductImport uploadProducts(int id) {
        ProductImport productImport = productImportRepository.get(id);
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .preferNullOverDefault(true)
                .skip(2)
                .build();
        List<XlsProduct> xlsProducts = Poiji.fromExcel(new File(productImport.getFile()), XlsProduct.class, options);
        List<Product> products = new ArrayList<>();
        xlsProducts.forEach(xlsProduct -> {
            try {
                Product product = new Product();
                if (xlsProduct.getId() != null) {
                    product = productService.get(xlsProduct.getId());
                } else if (xlsProduct.getSku() != null) {
                    product = productService.getBySku(xlsProduct.getSku());
                }
                if (product == null) {
                    product = new Product();
                }
                product = xlsToProductMapper.map(xlsProduct, product);
                if (product != null) {
                    Set<Category> categories = product.getCategories().stream().distinct().map(category -> {
                        Category persisted = categoryService.getByName(category.getName());
                        if (persisted != null) {
                            return persisted;
                        }
                        return category;
                    }).collect(Collectors.toSet());
                    product.setCategories(categories);
                    categoryService.updateAll(new ArrayList<>(categories));
                    productImport.setUploadedCategories(new ArrayList<>(categories));
                    products.add(product);
                }
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        });
//        categoryService.updateAll(categories);
        productService.updateAll(products);
        productImport.setUploadedProducts(products);
        return productImport;
    }
}
