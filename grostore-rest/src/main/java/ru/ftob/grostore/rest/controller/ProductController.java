package ru.ftob.grostore.rest.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.model.product.ProductImportFieldType;
import ru.ftob.grostore.rest.webmodel.GuiProduct;
import ru.ftob.grostore.service.product.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(Pageable pageable) {
        List<GuiProduct> guiProducts = productService.getAll(pageable).stream().map(
                p -> modelMapper.map(p, GuiProduct.class)
        ).collect(Collectors.toList());

        return ResponseEntity.ok(guiProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.get(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.create(product));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody GuiProduct product
    ) {
        productService.update(modelMapper.map(product, Product.class));
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/fields")
    public ResponseEntity<?> getProductFields() {
        return ResponseEntity.ok(ProductImportFieldType.values());
    }
}
