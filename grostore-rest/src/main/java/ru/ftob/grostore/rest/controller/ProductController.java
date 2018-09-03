package ru.ftob.grostore.rest.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
        Page<Product> products = productService.getAll(pageable);
        List<GuiProduct> guiProducts = products.stream().map(
                p -> modelMapper.map(p, GuiProduct.class)
        ).collect(Collectors.toList());
        PageImpl page = new PageImpl(guiProducts, pageable, products.getTotalElements());

        return ResponseEntity.ok(page);
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

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll(@RequestBody List<GuiProduct> products) {
        productService.deleteAll(products.stream().map(g -> modelMapper.map(g, Product.class)).collect(Collectors.toList()));
        return ResponseEntity.ok().build();
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
