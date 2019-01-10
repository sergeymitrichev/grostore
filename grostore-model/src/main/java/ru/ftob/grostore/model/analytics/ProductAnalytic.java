package ru.ftob.grostore.model.analytics;


import ru.ftob.grostore.model.base.AbstractBaseEntity;
import ru.ftob.grostore.model.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "product_analytic")
public class ProductAnalytic extends AbstractBaseEntity {

    @MapsId
    @OneToOne(mappedBy = "analytic")
    @JoinColumn(name = "id")
    private Product product;

    @Column(name = "add_to_cart")
    private Long additionsToCart;

    @Column(name = "add_to_cart_quantity")
    private Long additionsToCartQuantity;

    @Column(name = "sales")
    private Long sales;

    @Column(name = "sales_quantity")
    private Long salesQuantity;

    @Column(name = "deliveries")
    private Long deliveries;

    @Column(name = "deliveries_quantity")
    private Long deliveriesQuantity;

    @Column(name = "out_of_stocks")
    private Long outOfStocks;

    @Column(name = "price_inc")
    private Long priceIncreases;

    @Column(name = "price_dec")
    private Long priceDecreases;

    @Column(name = "views")
    private Long views;

    public ProductAnalytic() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getAdditionsToCart() {
        return additionsToCart;
    }

    public void setAdditionsToCart(Long additionsToCart) {
        this.additionsToCart = additionsToCart;
    }

    public Long getAdditionsToCartQuantity() {
        return additionsToCartQuantity;
    }

    public void setAdditionsToCartQuantity(Long additionsToCartQuantity) {
        this.additionsToCartQuantity = additionsToCartQuantity;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Long getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(Long salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public Long getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Long deliveries) {
        this.deliveries = deliveries;
    }

    public Long getDeliveriesQuantity() {
        return deliveriesQuantity;
    }

    public void setDeliveriesQuantity(Long deliveriesQuantity) {
        this.deliveriesQuantity = deliveriesQuantity;
    }

    public Long getOutOfStocks() {
        return outOfStocks;
    }

    public void setOutOfStocks(Long outOfStocks) {
        this.outOfStocks = outOfStocks;
    }

    public Long getPriceIncreases() {
        return priceIncreases;
    }

    public void setPriceIncreases(Long priceIncreases) {
        this.priceIncreases = priceIncreases;
    }

    public Long getPriceDecreases() {
        return priceDecreases;
    }

    public void setPriceDecreases(Long priceDecreases) {
        this.priceDecreases = priceDecreases;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }
}
