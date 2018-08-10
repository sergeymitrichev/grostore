package ru.ftob.grostore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ftob.grostore.model.product.ProductImport;
import ru.ftob.grostore.model.product.ProductImportField;
import ru.ftob.grostore.model.product.ProductImportFieldType;
import ru.ftob.grostore.service.util.exception.StorageFileNotFoundException;
import ru.ftob.grostore.service.StorageService;
import ru.ftob.grostore.service.ProductImportService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imports")
public class ProductImportController {

    private final StorageService storageService;

    private ProductImportService productImportService;

    @Autowired
    public ProductImportController(StorageService storageService, ProductImportService productImportService) {
        this.storageService = storageService;
        this.productImportService = productImportService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productImportService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(productImportService.get(id));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestParam("productImport") ProductImport productImport,
            @RequestParam("file") MultipartFile file
    ) {
        storageService.store(file);
        productImport.setFile(file.getOriginalFilename());
        return ResponseEntity.ok(productImportService.create(productImport));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestParam("productImport") ProductImport productImport,
            @RequestParam(value = "file") Optional<MultipartFile> file
    ) {
        file.ifPresent(v -> {
            storageService.store(v);
            productImport.setFile(v.getOriginalFilename());
        });
        productImportService.update(productImport);
        try {
            return ResponseEntity.ok(productImportService.get(id));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<?> upload(@PathVariable Integer id) {
        productImportService.uploadProducts(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fields")
    public ResponseEntity<?> getProductFields() {
        return ResponseEntity.ok(
                Arrays.stream(ProductImportFieldType.values())
                        .map(ProductImportField::new)
                        .collect(Collectors.toList()));
    }




//    @PostMapping("/create/old")
//    public ResponseEntity<?> create(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
//        return ResponseEntity.ok(handler.create(file, name));
//    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }


    // TODO upload files http://spring.io/guides/gs/uploading-files/
}
