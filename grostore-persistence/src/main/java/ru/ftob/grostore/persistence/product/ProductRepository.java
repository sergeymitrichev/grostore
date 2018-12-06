package ru.ftob.grostore.persistence.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.model.product.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    List<Product> saveAll(List<Product> products);

    boolean delete(int id);

    void deleteAll(List<Product> products);

    Product get(int id);

    Page<Product> getAll(Pageable pageable);

    Product getBySku(String sku);

    List<Product> getAllBySku(List<String> sku);

    List<Product> getAllByUpdatedBy(Account updatedBy);
}
