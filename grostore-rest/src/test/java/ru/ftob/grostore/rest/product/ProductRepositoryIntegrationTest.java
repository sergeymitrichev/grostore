package ru.ftob.grostore.rest.product;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.persistence.product.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryIntegrationTest {

    private final Product PRODUCT = new Product();
    private final String PRODUCT_NAME = "New Product";
    private final String PRODUCT_SKU = "Product SKU";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;


    @Before
    public void init() {
        PRODUCT.setName(PRODUCT_NAME);
        PRODUCT.setSku(PRODUCT_SKU);
    }

    @Test
    public void whenFindById_thenReturnProduct() {
        Integer id = (Integer) entityManager.persistAndGetId(PRODUCT);
        Product found = productRepository.findById(id).get();
        Assertions.assertThat(found).isEqualTo(PRODUCT);
    }

    @Test
    public void whenFindBySku_thenReturnProduct() {
        entityManager.persistAndGetId(PRODUCT);
        Product found = productRepository.findBySku(PRODUCT_SKU).get();
        Assertions.assertThat(found).isEqualTo(PRODUCT);
    }
}
