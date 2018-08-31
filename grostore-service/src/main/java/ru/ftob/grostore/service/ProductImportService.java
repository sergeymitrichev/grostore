package ru.ftob.grostore.service;

import ru.ftob.grostore.model.product.ProductImport;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface ProductImportService {

    ProductImport create(ProductImport productImport);

    void delete(int id) throws NotFoundException;

    ProductImport get(int id) throws NotFoundException, IOException;

    void update(ProductImport productImport);

    List<ProductImport> getAll();

    ProductImport getWithProducts(int id);

    ProductImport uploadProducts(int id);
}
