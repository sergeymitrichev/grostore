package ru.ftob.grostore.model.analytics;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ftob.grostore.model.product.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductAnalyticTest {

    private ProductAnalytic productAnalytic;
    private Product product;
    private final Long TEST_LONG = 100000L;
    private final Long TEST_LONG_CHANGED = TEST_LONG + 10;
    private final Integer TEST_INTEGER = 100;
    private final String TEST_STRING = "String";

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(TEST_INTEGER);
        product.setSku(TEST_STRING);
        product.setName(TEST_STRING);
        productAnalytic = new ProductAnalytic();
        productAnalytic.setAdditionsToCart(TEST_LONG);
        productAnalytic.setAdditionsToCartQuantity(TEST_LONG);
        productAnalytic.setDeliveries(TEST_LONG);
        productAnalytic.setDeliveriesQuantity(TEST_LONG);
        productAnalytic.setSales(TEST_LONG);
        productAnalytic.setSalesQuantity(TEST_LONG);
        productAnalytic.setViews(TEST_LONG);
        productAnalytic.setOutOfStocks(TEST_LONG);
        productAnalytic.setPriceDecreases(TEST_LONG);
        productAnalytic.setPriceIncreases(TEST_LONG);
        productAnalytic.setProduct(product);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProduct() {
        assertEquals(product, productAnalytic.getProduct());
    }

    @Test
    void setProduct() {
        Product newProduct = new Product();
        newProduct.setId(TEST_INTEGER + 1);
        newProduct.setSku(TEST_STRING + " edited");
        productAnalytic.setProduct(newProduct);
        assertEquals(newProduct, productAnalytic.getProduct());
    }

    @Test
    void getAdditionsToCart() {
        assertEquals(TEST_LONG, productAnalytic.getAdditionsToCart());
    }

    @Test
    void setAdditionsToCart() {
        productAnalytic.setAdditionsToCart(TEST_LONG_CHANGED);
        assertEquals(TEST_LONG_CHANGED, productAnalytic.getAdditionsToCart());
    }

    @Test
    void getAdditionsToCartQuantity() {
        assertEquals(TEST_LONG, productAnalytic.getAdditionsToCartQuantity());
    }

    @Test
    void setAdditionsToCartQuantity() {
        productAnalytic.setAdditionsToCartQuantity(TEST_LONG_CHANGED);
        assertEquals(TEST_LONG_CHANGED, productAnalytic.getAdditionsToCartQuantity());
    }

    @Test
    void getSales() {
        assertEquals(TEST_LONG, productAnalytic.getSales());
    }

    @Test
    void setSales() {
        productAnalytic.setSales(TEST_LONG_CHANGED);
        assertEquals(TEST_LONG_CHANGED, productAnalytic.getSales());
    }

    @Test
    void getSalesQuantity() {
        assertEquals(TEST_LONG, productAnalytic.getSalesQuantity());
    }

    @Test
    void setSalesQuantity() {
        productAnalytic.setSalesQuantity(TEST_LONG_CHANGED);
        assertEquals(TEST_LONG_CHANGED, productAnalytic.getSalesQuantity());
    }

    @Test
    void getDeliveries() {
        assertEquals(TEST_LONG, productAnalytic.getDeliveries());
    }

    @Test
    void setDeliveries() {
        productAnalytic.setDeliveries(TEST_LONG_CHANGED);
        assertEquals(TEST_LONG_CHANGED, productAnalytic.getDeliveries());
    }

    @Test
    void getDeliveriesQuantity() {
        assertEquals(TEST_LONG, productAnalytic.getDeliveriesQuantity());
    }

    @Test
    void setDeliveriesQuantity() {
        productAnalytic.setDeliveriesQuantity(TEST_LONG_CHANGED);
        assertEquals(TEST_LONG_CHANGED, productAnalytic.getDeliveriesQuantity());
    }

    @Test
    void getOutOfStocks() {
        assertEquals(TEST_LONG, productAnalytic.getOutOfStocks());
    }

    @Test
    void setOutOfStocks() {
        productAnalytic.setOutOfStocks(TEST_LONG_CHANGED);
        assertEquals(TEST_LONG_CHANGED, productAnalytic.getOutOfStocks());
    }

    @Test
    void getPriceIncreases() {
        assertEquals(TEST_LONG, productAnalytic.getPriceIncreases());
    }

    @Test
    void setPriceIncreases() {
        productAnalytic.setPriceIncreases(TEST_LONG_CHANGED);
        assertEquals(TEST_LONG_CHANGED, productAnalytic.getPriceIncreases());
    }

    @Test
    void getPriceDecreases() {
        assertEquals(TEST_LONG, productAnalytic.getPriceDecreases());
    }

    @Test
    void setPriceDecreases() {
        productAnalytic.setPriceDecreases(TEST_LONG_CHANGED);
        assertEquals(TEST_LONG_CHANGED, productAnalytic.getPriceDecreases());
    }

    @Test
    void getViews() {
        assertEquals(TEST_LONG, productAnalytic.getViews());
    }

    @Test
    void setViews() {
        productAnalytic.setViews(TEST_LONG_CHANGED);
        assertEquals(TEST_LONG_CHANGED, productAnalytic.getViews());
    }
}