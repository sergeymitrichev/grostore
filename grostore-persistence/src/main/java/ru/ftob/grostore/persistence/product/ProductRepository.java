package ru.ftob.grostore.persistence.product;

import ru.ftob.grostore.model.product.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    List<Product> saveAll(List<Product> products);

    boolean delete(int id);

    Product get(int id);

    List<Product> getAll();
}
