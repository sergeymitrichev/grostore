package ru.ftob.grostore.rest.webmodel;

import java.util.ArrayList;
import java.util.List;

public class GuiProductImport {
    private List<GuiProduct> products = new ArrayList<>();
    private List<GuiCategory> categories = new ArrayList<>();

    public GuiProductImport() {
    }

    public void addProduct(GuiProduct product){
        products.add(product);
    }

    public void addCategory(GuiCategory category){
        categories.add(category);
    }

    public List<GuiProduct> getProducts() {
        return products;
    }

    public void setProducts(List<GuiProduct> products) {
        this.products = products;
    }

    public List<GuiCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<GuiCategory> categories) {
        this.categories = categories;
    }
}
