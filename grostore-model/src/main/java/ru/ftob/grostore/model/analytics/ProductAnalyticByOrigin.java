package ru.ftob.grostore.model.analytics;

import ru.ftob.grostore.model.base.AbstractBaseEntity;
import ru.ftob.grostore.model.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(
        name = "product_analytic_by_origin",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"product_id", "origin"},
                name = "product_analytic_unique_product_id_origin_idx")})
public class ProductAnalyticByOrigin extends AbstractBaseEntity implements Viewable, Saleable, Storable {

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "origin")
    @Size(min = 4, max = 16)
    @NotEmpty
    private String origin;

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

    @Override
    public String getOrigin() {
        return origin;
    }

    @Override
    public Long getAdditionsToCart() {
        return additionsToCart;
    }

    @Override
    public Long getAdditionsToCartQuantity() {
        return additionsToCartQuantity;
    }

    @Override
    public Long getSales() {
        return sales;
    }

    @Override
    public Long getSalesQuantity() {
        return salesQuantity;
    }

    @Override
    public Long getDeliveries() {
        return deliveries;
    }

    @Override
    public Long getQuantityDeliveries() {
        return deliveriesQuantity;
    }

    @Override
    public Long getOutOfStocks() {
        return outOfStocks;
    }

    @Override
    public Long getPriceIncreases() {
        return priceIncreases;
    }

    @Override
    public Long getPriceDecreases() {
        return priceDecreases;
    }

    @Override
    public Long getViews() {
        return views;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setAdditionsToCart(Long additionsToCart) {
        this.additionsToCart = additionsToCart;
    }

    public void setAdditionsToCartQuantity(Long additionsToCartQuantity) {
        this.additionsToCartQuantity = additionsToCartQuantity;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public void setSalesQuantity(Long salesQuantity) {
        this.salesQuantity = salesQuantity;
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

    public void setOutOfStocks(Long outOfStocks) {
        this.outOfStocks = outOfStocks;
    }

    public void setPriceIncreases(Long priceIncreases) {
        this.priceIncreases = priceIncreases;
    }

    public void setPriceDecreases(Long priceDecreases) {
        this.priceDecreases = priceDecreases;
    }

    public void setViews(Long views) {
        this.views = views;
    }
}
