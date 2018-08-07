package ru.ftob.grostore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ftob.grostore.handler.XlsHandler;
import ru.ftob.grostore.model.product.ProductImport;
import ru.ftob.grostore.model.product.ProductImportField;
import ru.ftob.grostore.model.product.ProductImportFieldType;
import ru.ftob.grostore.rest.service.StorageService;
import ru.ftob.grostore.service.ProductImportService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImportControllerHandler {

    private final StorageService storageService;

    private final ProductImportService productImportService;

    @Autowired
    public ProductImportControllerHandler(StorageService storageService, ProductImportService productImportService) {
        this.storageService = storageService;
        this.productImportService = productImportService;
    }

    public ProductImport create(MultipartFile file, String name) {
        //TODO set unique filename
        storageService.store(file);
        //TODO save raw as JSON file or tmp SQL table
        XlsHandler xlsHandler = new XlsHandler(file);
        ProductImport productImport = new ProductImport();
        productImport.setName(name);
        productImport.setFile(file.getOriginalFilename());
        productImport.setRaw(xlsHandler.getRaw());
        return productImportService.create(productImport);
    }

    public ProductImport update(ProductImport productImport) {
        productImportService.update(productImport);
        ProductImport updated = productImportService.get(productImport.getId());
        updated.setRaw(productImport.getRaw());
        return updated;
    }

    public List<ProductImportField> getProductFields() {
        return Arrays.stream(ProductImportFieldType.values()).map(f -> new ProductImportField(f)).collect(Collectors.toList());

    }

    public ProductImport get(Integer id) {
        ProductImport productImport = productImportService.get(id);
        XlsHandler xlsHandler = new XlsHandler(storageService.load(productImport.getFile()));
        productImport.setRaw(xlsHandler.getRaw());
        productImport.setRowLength(xlsHandler.getMaxNrCols());
        return productImport;
    }
}
