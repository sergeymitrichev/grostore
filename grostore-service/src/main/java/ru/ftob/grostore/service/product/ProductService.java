package ru.ftob.grostore.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.List;

public interface ProductService {

    Product create(Product product);

    void delete(int id) throws NotFoundException;

    void deleteAll(List<Product> products) throws NotFoundException;

    Product get(int id) throws NotFoundException;

    void update(Product product);

    void updateAll(List<Product> products);

    Page<Product> getAll(Pageable pageable);

    Product getBySku(String sku);
}
