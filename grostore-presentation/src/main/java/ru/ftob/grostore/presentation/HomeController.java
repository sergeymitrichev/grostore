package ru.ftob.grostore.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.service.product.ProductService;

import java.util.Map;

@Controller
public class HomeController {

    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Map<String, Page<Product>> model) {
        model.put("products", productService.getAll(new QPageRequest(0, 10)));
        return "home";
    }
}
