package ru.ftob.grostore.ucoz.handler;

import ru.ftob.grostore.ucoz.snapshot.SnapshotProduct;

import java.util.List;

public interface SnapshotHandler {

    void updateProduct(SnapshotProduct product);

    void updateAllProducts(List<SnapshotProduct> products, String stock);

    void updateProductPrices(List<SnapshotProduct> products);

    Iterable<SnapshotProduct> persistAllProducts(List<SnapshotProduct> products);
}
