package ru.ftob.grostore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ftob.grostore.handler.XlsHandler;
import ru.ftob.grostore.model.product.ProductImport;
import ru.ftob.grostore.rest.service.StorageService;
import ru.ftob.grostore.service.ProductImportService;

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
        storageService.store(file);
        XlsHandler xlsHandler = new XlsHandler(file);
        ProductImport productImport = new ProductImport();
        productImport.setName(name);
        productImport.setFile(file.getOriginalFilename());
        productImport.setRaw(xlsHandler.getRaw());
        return productImportService.create(productImport);
    }
}
