package ru.ftob.grostore.service.product;

import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.service.BaseService;

import java.util.List;
import java.util.Optional;

public interface ProductService extends BaseService<Product, Integer> {

    Optional<Product> getBySku(String sku);

    List<Product> getAllBySku(List<String> sku);

    List<Product> getAllByUpdatedBy(Account updatedBy);

}
