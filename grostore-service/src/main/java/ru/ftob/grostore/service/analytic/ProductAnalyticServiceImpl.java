package ru.ftob.grostore.service.analytic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.analytics.ProductAnalyticByOrigin;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.persistence.analytic.ProductAnalyticRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.Collection;

import static ru.ftob.grostore.service.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductAnalyticServiceImpl implements ProductAnalyticService {

    private final ProductAnalyticRepository repository;

    @Autowired
    public ProductAnalyticServiceImpl(ProductAnalyticRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<ProductAnalyticByOrigin> getByAllProduct(Product product, Pageable pageable) {
        Assert.notNull(product, "Product must not be null");
        return repository.findAllByProduct(product, pageable);
    }

    @Override
    public ProductAnalyticByOrigin create(ProductAnalyticByOrigin productAnalyticByOrigin) {
        Assert.notNull(productAnalyticByOrigin, "Product analytic must not be null");
        return repository.save(productAnalyticByOrigin);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ProductAnalyticByOrigin update(ProductAnalyticByOrigin productAnalyticByOrigin) {
        Assert.notNull(productAnalyticByOrigin, "Product analytic must not be null");
        return checkNotFoundWithId(repository.save(productAnalyticByOrigin), productAnalyticByOrigin.getId());
    }

    @Override
    public Collection<ProductAnalyticByOrigin> updateAll(Collection<ProductAnalyticByOrigin> t) {
        Assert.notNull(t, "Product analytic collection must not be null");
        return repository.saveAll(t);
    }

    @Override
    public ProductAnalyticByOrigin get(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found entity with id = " + id));
    }

    @Override
    public Page<ProductAnalyticByOrigin> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Collection<ProductAnalyticByOrigin> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public void deleteAll(Collection<ProductAnalyticByOrigin> t) {
        Assert.notNull(t, "Product collection list must not be null");
        repository.deleteAll(t);
    }
}
