package ru.ftob.grostore.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.persistence.product.ProductRepository;
import ru.ftob.grostore.persistence.productlist.CategoryRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.List;

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
        product.getPrices().stream().forEach(price -> {
            price.setProduct(product);
        });

        return productRepository.save(product);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(productRepository.delete(id), id);
    }

    @Override
    public void deleteAll(List<Product> products) throws NotFoundException {
        productRepository.deleteAll(products);
    }

    @Override
    public Product get(int id) throws NotFoundException {
        return checkNotFoundWithId(productRepository.get(id), id);
    }

    @Override
    public void update(Product product) {
        Assert.notNull(product, "Product must not be null");
        checkNotFoundWithId(productRepository.save(product), product.getId());
    }

    @Override
    public void updateAll(List<Product> products) {
        Assert.notNull(products, "Product list must not be null");
        productRepository.saveAll(products);
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.getAll(pageable);
    }

    @Override
    public Product getBySku(String sku) {
        return productRepository.getBySku(sku);
    }
}
