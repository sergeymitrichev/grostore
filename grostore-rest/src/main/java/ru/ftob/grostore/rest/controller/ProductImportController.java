package ru.ftob.grostore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ftob.grostore.rest.exception.StorageFileNotFoundException;
import ru.ftob.grostore.rest.service.StorageService;

@RestController
@RequestMapping("/imports")
public class ProductImportController {

    private final StorageService storageService;

    private ProductImportControllerHandler handler;

    @Autowired
    public ProductImportController(StorageService storageService, ProductImportControllerHandler handler) {
        this.storageService = storageService;
        this.handler = handler;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
        return ResponseEntity.ok(handler.create(file, name));
    }

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
