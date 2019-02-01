package ru.ftob.grostore.model.analytics;

public interface Saleable {

    String getOrigin();

    Long getAdditionsToCart();

    Long getAdditionsToCartQuantity();

    Long getSales();

    Long getSalesQuantity();

    Long getDeliveries();

    Long getQuantityDeliveries();

}
