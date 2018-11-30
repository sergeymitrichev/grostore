package ru.ftob.grostore.rest.webmodel;

import java.util.List;

public class GuiStockWithProducts extends GuiStock {

    private List<GuiProductInStock> products;

    public GuiStockWithProducts() {
    }

    public List<GuiProductInStock> getProducts() {
        return products;
    }

    public void setProducts(List<GuiProductInStock> products) {
        this.products = products;
    }
}
