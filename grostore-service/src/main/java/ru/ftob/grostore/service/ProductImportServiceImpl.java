package ru.ftob.grostore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.product.ProductImport;
import ru.ftob.grostore.persistence.ProductImportRepository;
import ru.ftob.grostore.util.exception.NotFoundException;

import java.util.List;

import static ru.ftob.grostore.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductImportServiceImpl implements ProductImportService {

    private final ProductImportRepository repository;

    @Autowired
    public ProductImportServiceImpl(ProductImportRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductImport create(ProductImport productImport) {
        Assert.notNull(productImport, "Product Import must not be null");
        return repository.save(productImport);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public ProductImport get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void update(ProductImport productImport) {
        Assert.notNull(productImport, "Product Import must not be null");
        checkNotFoundWithId(repository.save(productImport), productImport.getId());
    }

    @Override
    public List<ProductImport> getAll() {
        return repository.getAll();
    }

    @Override
    public ProductImport getWithProducts(int id) {
        return checkNotFoundWithId(repository.getWithProducts(id), id);
    }
}
