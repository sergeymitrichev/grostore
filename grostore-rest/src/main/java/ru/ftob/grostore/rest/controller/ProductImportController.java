package ru.ftob.grostore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ftob.grostore.model.product.ProductImport;
import ru.ftob.grostore.model.product.ProductImportFieldType;
import ru.ftob.grostore.rest.storage.StorageService;
import ru.ftob.grostore.rest.webmodel.GuiCategory;
import ru.ftob.grostore.rest.webmodel.GuiProduct;
import ru.ftob.grostore.rest.webmodel.GuiProductImport;
import ru.ftob.grostore.service.ProductImportService;
import ru.ftob.grostore.service.util.exception.StorageFileNotFoundException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imports")
public class ProductImportController extends AbstractRestController {

    private final StorageService storageService;

    private ProductImportService productImportService;

    @Autowired
    public ProductImportController(StorageService storageService, ProductImportService productImportService) {
        this.storageService = storageService;
        this.productImportService = productImportService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<ProductImport> productImports = productImportService.getAll();
        List<GuiProductImport> guiProductImports = productImports.stream().map(
                t -> getMapper().map(t, GuiProductImport.class)
        ).collect(Collectors.toList());

        return ResponseEntity.ok(guiProductImports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(productImportService.get(id));
        }  catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestParam("file") MultipartFile file, @RequestParam("name") String name
    ) {
        storageService.store(file);
        ProductImport productImport = new ProductImport();
        productImport.setName(name);
        productImport.setFile(storageService.getRootLocation() + "/" + file.getOriginalFilename());
        return ResponseEntity.ok(productImportService.create(productImport));
    }

    @PostMapping("/{id}/file")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestParam("file") MultipartFile file
    ) {
        storageService.store(file);
        try {
            ProductImport productImport = productImportService.get(id);
            productImportService.update(productImport);
            productImport.setFile(storageService.getRootLocation() + "/" + file.getOriginalFilename());
            return ResponseEntity.ok(productImportService.get(id));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody ProductImport productImport
    ) {
        productImportService.update(productImport);
        try {
            return ResponseEntity.ok(productImportService.get(id));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        productImportService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<?> upload(@PathVariable Integer id) {
        ProductImport productImport = productImportService.uploadProducts(id);
        GuiProductImport guiProductImport = new GuiProductImport();
        //TODO Add validate
        guiProductImport.setCategories(productImport.getUploadedCategories().stream().map(c-> new GuiCategory(c.getName())).collect(Collectors.toList()));
        guiProductImport.setProducts(productImport.getUploadedProducts().stream().map(p-> new GuiProduct(p.getName(), p.getSku(), Collections.emptyList())).collect(Collectors.toList()));
        return ResponseEntity.ok(guiProductImport);
    }

    @GetMapping("/fields")
    public ResponseEntity<?> getProductFields() {
        return ResponseEntity.ok(ProductImportFieldType.values());
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
}
