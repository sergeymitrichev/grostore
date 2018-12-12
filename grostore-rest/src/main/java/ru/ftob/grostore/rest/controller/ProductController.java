package ru.ftob.grostore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.model.product.ProductImportFieldType;
import ru.ftob.grostore.rest.util.ModelMapperUtils;
import ru.ftob.grostore.rest.webmodel.GuiProduct;
import ru.ftob.grostore.rest.webmodel.GuiProductSimple;
import ru.ftob.grostore.service.product.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController extends AbstractRestController<Product, Integer, GuiProduct> {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
        setService(service);
    }

    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok(
                ModelMapperUtils.mapPage(
                        service.getAll(pageable),
                        GuiProductSimple.class,
                        pageable));
    }

    @GetMapping("/fields")
    public ResponseEntity<?> getProductFields() {
        return ResponseEntity.ok(ProductImportFieldType.values());
    }
}
