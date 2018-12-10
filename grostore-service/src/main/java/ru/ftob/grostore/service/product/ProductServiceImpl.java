package ru.ftob.grostore.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.persistence.product.ProductRepository;
import ru.ftob.grostore.persistence.productlist.CategoryRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static ru.ftob.grostore.service.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product create(Product product) {
        Assert.notNull(product, "Product must not be null");
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        checkNotFoundWithId(productRepository.delete(id), id);
    }

    @Override
    public void deleteAll(Collection<Product> products) throws NotFoundException {
        productRepository.deleteAll(products);
    }

    @Override
    public Product get(Integer id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id not found: " + id));
    }

    @Override
    public Product update(Product product) {
        Assert.notNull(product, "Product must not be null");
        return productRepository.save(product);
    }

    @Override
    public Collection<Product> updateAll(Collection<Product> products) {
        Assert.notNull(products, "Product list must not be null");
        Assert.notEmpty(products, "Product list must not be empty");
        products.forEach(product -> {
            if(product.isNew()) {
                Product duplicate = productRepository.findBySku(product.getSku()).orElse(null);
                if (duplicate != null) {
                    product.setId(duplicate.getId());
                }
            }
        });
        return productRepository.saveAll(products);
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Collection<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getBySku(String sku) {
        return productRepository.findBySku(sku);
    }

    @Override
    public List<Product> getAllBySku(List<String> sku) {
        return productRepository.findAllBySku(sku);
    }

    @Override
    public List<Product> getAllByUpdatedBy(Account updatedBy) {
        return productRepository.findAllByUpdatedBy(updatedBy);
    }
}
