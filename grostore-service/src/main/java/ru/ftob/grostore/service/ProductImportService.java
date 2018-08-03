package ru.ftob.grostore.service;

import ru.ftob.grostore.model.product.ProductImport;
import ru.ftob.grostore.util.exception.NotFoundException;

import java.util.List;

public interface ProductImportService {

    ProductImport create(ProductImport productImport);

    void delete(int id) throws NotFoundException;

    ProductImport get(int id) throws NotFoundException;

    void update(ProductImport productImport);

    List<ProductImport> getAll();

    ProductImport getWithProducts(int id);
}
