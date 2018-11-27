package ru.ftob.grostore.rest.webmodel;

public class GuiProductSimple extends GuiAbstractNamedEntity {
    private String sku;

    public GuiProductSimple() {
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
