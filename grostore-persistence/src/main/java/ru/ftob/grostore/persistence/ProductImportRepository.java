package ru.ftob.grostore.persistence;

import ru.ftob.grostore.model.product.ProductImport;

import java.util.List;

public interface ProductImportRepository {

    ProductImport save(ProductImport productImport);

    boolean delete(int id);

    ProductImport get(int id);

    List<ProductImport> getAll();

    default ProductImport getWithProducts(int id) {
        throw new UnsupportedOperationException();
    }
}
