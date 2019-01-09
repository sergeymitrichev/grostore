package ru.ftob.grostore.model.analytics;

import ru.ftob.grostore.model.base.AbstractBaseEntity;

public class ProductAnalytic extends AbstractBaseEntity implements Viewable, Saleable, Storable {
    @Override
    public Long getAdditionsToCart() {
        return null;
    }

    @Override
    public String getAdditionsToCartOrigin() {
        return null;
    }

    @Override
    public Long getAdditionsToCartQuantity() {
        return null;
    }

    @Override
    public String getSalesOrigin() {
        return null;
    }

    @Override
    public Long getSales() {
        return null;
    }

    @Override
    public Long getSalesQuantity() {
        return null;
    }

    @Override
    public Long getDeliveries() {
        return null;
    }

    @Override
    public String getDeliveriesOrigin() {
        return null;
    }

    @Override
    public Long getQuantityDeliveries() {
        return null;
    }

    @Override
    public Long getOutOfStocks() {
        return null;
    }

    @Override
    public Long getPriceIncreases() {
        return null;
    }

    @Override
    public Long getPriceDecreases() {
        return null;
    }

    @Override
    public Long getViews() {
        return null;
    }
}
