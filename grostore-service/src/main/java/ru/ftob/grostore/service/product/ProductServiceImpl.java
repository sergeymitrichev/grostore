package ru.ftob.grostore.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.persistence.product.ProductRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;
import ru.ftob.grostore.service.validator.ProductValidator;

import java.util.List;

import static ru.ftob.grostore.service.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    @Qualifier("productValidator")
    private ProductValidator validator;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public Product create(Product product) {
        Assert.notNull(product, "Product must not be null");
        return repository.save(product);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Product get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void update(Product product) {
        Assert.notNull(product, "Product must not be null");
        checkNotFoundWithId(repository.save(product), product.getId());
    }

    @Override
    public void updateAll(List<Product> products) {
        Assert.notNull(products, "Product list must not be null");
        repository.saveAll(products);
    }

    @Override
    public List<Product> getAll() {
        return repository.getAll();
    }
}
