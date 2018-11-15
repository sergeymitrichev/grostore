package ru.ftob.grostore.ucoz.handler;

import ru.ftob.grostore.ucoz.snapshot.SnapshotProduct;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface SnapshotHandler {

    void updateProduct(SnapshotProduct product) throws InterruptedException, ExecutionException, IOException;

    void updateAllProducts(List<SnapshotProduct> products, String stock);

    Iterable<SnapshotProduct> persistAllProducts(List<SnapshotProduct> products);
}
