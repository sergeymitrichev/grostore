package ru.ftob.grostore.rest.webmodel;

import java.util.List;

public class GuiProduct {

    private Integer id;
    private String name;
    private String sku;
    private List<GuiCategory> categories;
    private List<GuiPrice> prices;

    public GuiProduct() {
    }

    public GuiProduct(String name, String sku, List<GuiCategory> categories) {
        this.name = name;
        this.sku = sku;
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<GuiCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<GuiCategory> categories) {
        this.categories = categories;
    }

    public List<GuiPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<GuiPrice> prices) {
        this.prices = prices;
    }
}
