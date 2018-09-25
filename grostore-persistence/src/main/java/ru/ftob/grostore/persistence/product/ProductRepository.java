package ru.ftob.grostore.persistence.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ftob.grostore.model.product.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    List<Product> saveAll(List<Product> products);

    boolean delete(int id);

    Product get(int id);

    Page<Product> getAll(Pageable pageable);

    Product getBySku(String sku);
}
