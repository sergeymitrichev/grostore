package ru.ftob.grostore.service;

import com.poiji.bind.Poiji;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.product.ProductImport;
import ru.ftob.grostore.persistence.ProductImportRepository;
import ru.ftob.grostore.service.util.XlsHandlerUtil;
import ru.ftob.grostore.service.util.exception.NotFoundException;
import ru.ftob.grostore.service.xlsto.XlsProduct;

import java.io.IOException;
import java.util.List;

import static ru.ftob.grostore.service.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductImportServiceImpl implements ProductImportService {

    private final ProductImportRepository repository;

    private final StorageService storageService;

    @Autowired
    public ProductImportServiceImpl(ProductImportRepository repository, StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }

    @Override
    public ProductImport create(ProductImport productImport) {
        Assert.notNull(productImport, "Product Import must not be null");
        return repository.save(productImport);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public ProductImport get(int id) throws NotFoundException, IOException {
        ProductImport productImport = checkNotFoundWithId(repository.get(id), id);
        productImport.setRaw(XlsHandlerUtil.getRaw(storageService.load(productImport.getFile())));
        return productImport;
    }

    @Override
    public void update(ProductImport productImport) {
        Assert.notNull(productImport, "Product Import must not be null");
        Assert.notNull(productImport.getFields(), "Product Import fields must not be null");
        Assert.notNull(productImport.getFile(), "Product Import file must not be null");
        try {
            XlsHandlerUtil.addFieldsToHeader(storageService.load(productImport.getFile()), productImport.getFields());
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkNotFoundWithId(repository.save(productImport), productImport.getId());
    }

    @Override
    public List<ProductImport> getAll() {
        return repository.getAll();
    }

    @Override
    public ProductImport getWithProducts(int id) {
        return checkNotFoundWithId(repository.getWithProducts(id), id);
    }

    @Override
    public void uploadProducts(int id) {
        ProductImport productImport = repository.get(id);
        List<XlsProduct> products = Poiji.fromExcel(storageService.load(productImport.getFile()).toFile(), XlsProduct.class);
        products.forEach(System.out::println);
        return;
    }
}
