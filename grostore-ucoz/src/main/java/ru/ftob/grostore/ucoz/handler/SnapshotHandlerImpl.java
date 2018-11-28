package ru.ftob.grostore.ucoz.handler;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.ucoz.exception.UApiRequestException;
import ru.ftob.grostore.ucoz.repository.ApiProductRepositoryImpl;
import ru.ftob.grostore.ucoz.snapshot.SnapshotProduct;
import ru.ftob.grostore.ucoz.snapshot.SnapshotProductRepository;
import ru.ftob.grostore.ucoz.to.UcozProduct;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SnapshotHandlerImpl implements SnapshotHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final SnapshotProductRepository snapshotProductRepository;

    private final ApiProductRepositoryImpl apiProductRepository;

    private final ModelMapper mapper = new ModelMapper();

    private final boolean HIDE_UAPI_PRODUCT_IF_NOT_FOUND = false;

    @Autowired
    public SnapshotHandlerImpl(SnapshotProductRepository snapshotProductRepository, ApiProductRepositoryImpl apiProductRepository) {
        this.snapshotProductRepository = snapshotProductRepository;
        this.apiProductRepository = apiProductRepository;
    }

    @Override
    public void updateProduct(SnapshotProduct product) throws InterruptedException, ExecutionException, IOException {

        Assert.notNull(product, "Snapshot product must not be null");

        SnapshotProduct updated = snapshotProductRepository.save(product);
        UcozProduct ucozProduct = new UcozProduct();
        mapper.map(updated, ucozProduct);
        try {
            apiProductRepository.save(ucozProduct);
        } catch (UApiRequestException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateAllProducts(List<SnapshotProduct> productsToUpdate, String stock) {

        Assert.notNull(productsToUpdate, "Snapshot product list must not be null");
        Assert.notEmpty(productsToUpdate, "Snapshot product list must not be empty");

        List<SnapshotProduct> persistedProducts = snapshotProductRepository.findAllByStock(stock);

        persistedProducts.forEach(p -> {
            if (productsToUpdate.contains(p)) {
                p.setPriceIn(productsToUpdate.get(productsToUpdate.lastIndexOf(p)).getPriceIn());
            } else if(HIDE_UAPI_PRODUCT_IF_NOT_FOUND) {
                p.setHide(true);
            }
            try {
                updateProduct(p);
            } catch (InterruptedException e) {
                log.error("Task was interrupted", e);
            } catch (ExecutionException e) {
                log.error("Can't parse api ucoz result", e);
            } catch (IOException e) {
                log.error("Can't connect to api ucoz", e);
            }
            log.debug("[" + p.getSku() + "]" + p.getName() + " updated");
        });
    }

    @Override
    public Iterable<SnapshotProduct> persistAllProducts(List<SnapshotProduct> products) {
        return snapshotProductRepository.saveAll(products);
    }
}
