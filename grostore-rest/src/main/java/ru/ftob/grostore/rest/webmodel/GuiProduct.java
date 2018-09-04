package ru.ftob.grostore.rest.webmodel;

public class GuiProduct {

    private Integer id;
    private String name;
    private String sku;
    private String categories;

    public GuiProduct() {
    }

    public GuiProduct(String name, String sku, String categories) {
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
