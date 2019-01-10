package ru.ftob.grostore.rest.webmodel;

public class GuiProductAnalytic {
    private Long additionsToCart;
    private Long additionsToCartQuantity;
    private Long sales;
    private Long salesQuantity;
    private Long deliveries;
    private Long deliveriesQuantity;
    private Long outOfStocks;
    private Long priceIncreases;
    private Long priceDecreases;
    private Long views;

    public GuiProductAnalytic() {
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
