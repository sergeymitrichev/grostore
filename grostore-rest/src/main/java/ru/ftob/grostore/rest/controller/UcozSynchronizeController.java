package ru.ftob.grostore.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.ftob.grostore.model.image.CategoryImage;
import ru.ftob.grostore.model.product.ProductImportFieldType;
import ru.ftob.grostore.model.productlist.Category;
import ru.ftob.grostore.rest.storage.StorageService;
import ru.ftob.grostore.ucoz.UcozSynchronizeService;
import ru.ftob.grostore.service.file.FileStorageServiceImpl;
import ru.ftob.grostore.service.product.ProductService;
import ru.ftob.grostore.service.productlist.CategoryService;
import ru.ftob.grostore.service.stock.StockService;
import ru.ftob.grostore.service.util.XlsHandlerUtil;
import ru.ftob.grostore.service.util.exception.NotFoundException;
import ru.ftob.grostore.service.xlsto.XlsProduct;
import ru.ftob.grostore.service.xlsto.XlsProductSnapshotRow;
import ru.ftob.grostore.ucoz.handler.SnapshotHandler;
import ru.ftob.grostore.ucoz.snapshot.SnapshotCategory;
import ru.ftob.grostore.ucoz.snapshot.SnapshotCategoryRepository;
import ru.ftob.grostore.ucoz.snapshot.SnapshotProduct;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/sync")
public class UcozSynchronizeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final StorageService storageService;

    private final FileStorageServiceImpl fileStorageService;

    private final SnapshotCategoryRepository snapshotCategoryRepository;

    private final ModelMapper mapper = new ModelMapper();

    private final SnapshotHandler handler;

    private final CategoryService categoryService;

    private final ProductService productService;

    private final StockService stockService;

    private final UcozSynchronizeService synchronizeService;

    @Autowired
    public UcozSynchronizeController(StorageService storageService, FileStorageServiceImpl fileStorageService, SnapshotCategoryRepository snapshotCategoryRepository, SnapshotHandler handler, CategoryService categoryService, ProductService productService, StockService stockService, UcozSynchronizeService synchronizeService) {
        this.storageService = storageService;
        this.fileStorageService = fileStorageService;
        this.snapshotCategoryRepository = snapshotCategoryRepository;
        this.handler = handler;
        this.categoryService = categoryService;
        this.productService = productService;
        this.stockService = stockService;
        this.synchronizeService = synchronizeService;
    }

    @PostMapping("/products/snapshot")
    public ResponseEntity<?> snapProducts(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        File tmp = new File(storageService.getRootLocation() + "/" + file.getOriginalFilename());
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .preferNullOverDefault(true)
                .skip(1)
                .build();
        List<XlsProductSnapshotRow> xlsProducts = Poiji.fromExcel(tmp, XlsProductSnapshotRow.class, options);
        AtomicReference<SnapshotCategory> category = new AtomicReference<>();
        List<SnapshotProduct> products = new ArrayList<>();
        xlsProducts.forEach(l -> {
            if (null == l.getSecond()) {
                if (l.getFirst() != null) {
                    category.set(snapshotCategoryRepository.findByName(l.getFirst()));
                }
            } else {
                int priceIn = 0;
                try {
                    priceIn = NumberFormat.getNumberInstance(Locale.FRANCE).parse(l.getFourth()).intValue();
                } catch (ParseException e) {
                    log.error("Cannot parse excel line: " + l.toString(), e);
                }
                String stock = "";
                switch (l.getFifth()) {
                    case "КД":
                        stock = "AUCHAN";
                        break;
                    case "СКЛАД":
                        stock = "STOCK";
                        break;
                    default:
                        stock = "METRO";
                }
                SnapshotProduct product = new SnapshotProduct(
                        Integer.valueOf(l.getFirst()),
                        l.getThird(),
                        category.get(),
                        l.getSecond(),
                        priceIn,
                        stock
                );
                products.add(product);
            }
        });
        return ResponseEntity.ok(handler.persistAllProducts(products));
    }

    @PostMapping("/categories/snapshot")
    public ResponseEntity<?> snapCategories(@RequestParam(defaultValue = "/var/lib/grostore/ucoz-categories-snashot.json") String filePath) {
        ResponseEntity<?> response = null;
        File tmp = new File(filePath);
        ObjectMapper mapper = new ObjectMapper();
        List<SnapshotCategory> categories = new ArrayList<>();
        try {
            String[][] read = mapper.readValue(tmp, String[][].class);
            Arrays.stream(read).forEach(r -> {
                categories.add(new SnapshotCategory(
                        Integer.valueOf(r[0]),
                        r[3],
                        r[5],
                        Integer.valueOf(r[1]),
                        Integer.valueOf(r[2]),
                        r[4],
                        Integer.valueOf(r[6]),
                        r[7]
                ));
            });
            response = ResponseEntity.ok(snapshotCategoryRepository.saveAll(categories));
        } catch (IOException e) {
            response = new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/products")
    public ResponseEntity<?> products(@RequestParam("file") MultipartFile file, @RequestParam List<ProductImportFieldType> fields, int toSkip, String stock) {
        storageService.store(file);
        log.debug("Reading from file with fields: " + fields);

        try {
            File tmp = new File(storageService.getRootLocation() + "/" + file.getOriginalFilename());
            XlsHandlerUtil.addFieldsToHeader(tmp, fields);
            PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                    .preferNullOverDefault(true)
                    .skip(toSkip)
                    .build();
            List<XlsProduct> xlsProducts = Poiji.fromExcel(tmp, XlsProduct.class, options);
            List<SnapshotProduct> productsToUpdate = new ArrayList<>();
            xlsProducts.forEach(x -> {
                SnapshotProduct p = new SnapshotProduct();
                mapper.map(x, p);
                if (p.getSku() != null && p.getPriceIn() != null) {
                    productsToUpdate.add(p);
                } else {

                }
            });
            handler.updateAllProducts(productsToUpdate, stock);

        } catch (IOException e) {
            log.error("Cannot open file", e);
        }
        log.debug("Finish reading from file " + file.getOriginalFilename());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/prices")
    public ResponseEntity<?> prices(@RequestParam("file") MultipartFile file, @RequestParam int skuColumn, @RequestParam int priceColumn) {
        storageService.store(file);
        log.debug("Reading prices from file. Sky column: [" + skuColumn + "], price column: [" + priceColumn + "].");

        try {
            File tmp = new File(storageService.getRootLocation() + "/" + file.getOriginalFilename());
            ArrayList<ProductImportFieldType> fields = new ArrayList<>();
            int i = 0;
            while (i <= Math.max(skuColumn, priceColumn)) {
                if (i == skuColumn) {
                    fields.add(ProductImportFieldType.SKU);
                } else if (i == priceColumn) {
                    fields.add(ProductImportFieldType.PRICE_IN);
                } else {
                    fields.add(ProductImportFieldType._IGNORE);
                }
                i++;
            }
            XlsHandlerUtil.addFieldsToHeader(tmp, fields);
            PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                    .preferNullOverDefault(true)
                    .build();
            List<XlsProduct> xlsProducts = Poiji.fromExcel(tmp, XlsProduct.class, options);
            List<SnapshotProduct> productsToUpdate = new ArrayList<>();
            xlsProducts.forEach(x -> {
                SnapshotProduct p = new SnapshotProduct();
                mapper.map(x, p);
                if (p.getSku() != null && p.getPriceIn() != null) {
                    productsToUpdate.add(p);
                }
            });
            handler.updateProductPrices(productsToUpdate);

        } catch (IOException e) {
            log.error("Cannot open file", e);
        }
        log.debug("Finish reading from file " + file.getOriginalFilename());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/categories")
    public ResponseEntity<?> syncCategoriesFromSnap() {
        List<Category> categories = new ArrayList<>();
        //TODO download backup and change to local host
        snapshotCategoryRepository.findAll().forEach(sc -> {
            categories.add(convertToDbCategory(sc));
        });
        categoryService.updateAll(categories);
        return ResponseEntity.ok().build();
    }

    private Category convertToDbCategory(SnapshotCategory sc) {
        Category category = new Category();
        category.setDescription(sc.getDescription());
        category.setHgu(sc.getUrl().replace("//minutka-nn.ru/shop", ""));
        category.setName(sc.getName());
        //TODO something
//        category.setMetaTitle(sc.getName());
        if (sc.getParentId() != 0) {
            category.setParent(
                    categoryService.getByName(
                            snapshotCategoryRepository.findById(
                                    sc.getParentId()).get().getName()));
        }
        if (!StringUtils.isEmpty(sc.getImageUrl())) {
            try {
                CategoryImage image = new CategoryImage();
                String imageName = "/img/c/" + sc.getId() + "." + fileStorageService.getFileExtension(sc.getImageUrl());
//                fileStorageService.store("http://minutka-nn.ru" + sc.getImageUrl(), "/img/c/" + sc.getId());
                image.setUrl(imageName);
                image.setEntity(category);
                image.setTitle(sc.getName());
                image.setAlt(sc.getName());
                category.addImage(image);
            } catch (Exception e) {
                log.error("Can't save image for category", e.getMessage());
            }
        }
        try {
            if (null == categoryService.getByName(category.getName())) {
                category = categoryService.create(category);
            } else {
                category = categoryService.update(category);
            }
        } catch (NotFoundException e) {
            category = categoryService.create(category);
        }

        return category;
    }

    @PostMapping("/products-from-backup")
    public ResponseEntity<?> productsFromBackup(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = new ByteArrayInputStream(file.getBytes());
            Workbook workbook = new XSSFWorkbook(inputStream);
            synchronizeService.saveProducts(workbook);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }
}
