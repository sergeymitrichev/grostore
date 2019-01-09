package ru.ftob.grostore.model.analytics;

public interface Saleable {

    Long getAdditionsToCart();

    String getAdditionsToCartOrigin();

    Long getAdditionsToCartQuantity();

    String getSalesOrigin();

    Long getSales();

    Long getSalesQuantity();

    Long getDeliveries();

    String getDeliveriesOrigin();

    Long getQuantityDeliveries();

}
