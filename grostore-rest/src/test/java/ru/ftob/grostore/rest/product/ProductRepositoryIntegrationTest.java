package ru.ftob.grostore.rest.product;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ftob.grostore.model.product.Price;
import ru.ftob.grostore.model.product.PriceType;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.persistence.product.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase
public class ProductRepositoryIntegrationTest {

    private final Product PRODUCT = new Product();
    private final String PRODUCT_NAME = "New Product";
    private final String PRODUCT_SKU = "Product SKU";
    private final Integer PRICE_VALUE = 10;
    private final PriceType PRICE_TYPE = PriceType.PRICE_TYPE_IN;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void init() {
        PRODUCT.setName(PRODUCT_NAME);
        PRODUCT.setSku(PRODUCT_SKU);
        PRODUCT.setMetaTitle(PRODUCT_NAME);

        List<Price> prices = new ArrayList<>();
        prices.add(new Price(PRICE_VALUE, PRICE_TYPE));
        PRODUCT.setPrices(prices);
        Product saved = productRepository.save(PRODUCT);
        PRODUCT.setId(saved.getId());
    }

    @Test
    public void whenFindById_thenReturnProduct() {
        assert PRODUCT.getId() != null;
        Product found = productRepository.findById(PRODUCT.getId()).orElse(null);
        Assertions.assertThat(found).isEqualTo(PRODUCT);
    }

    @Test
    public void whenFindBySku_thenReturnProduct() {
        assert PRODUCT.getSku() != null;
        Product found = productRepository.findBySku(PRODUCT.getSku()).orElse(null);
        Assertions.assertThat(found).isEqualTo(PRODUCT);
    }
}
