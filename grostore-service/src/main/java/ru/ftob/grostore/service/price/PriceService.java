package ru.ftob.grostore.service.price;

import ru.ftob.grostore.model.product.Product;

import java.util.List;

public interface PriceService {

    void generateOutFromAllProducts();

    void generateOutFromProducts(List<Product> products);
}
