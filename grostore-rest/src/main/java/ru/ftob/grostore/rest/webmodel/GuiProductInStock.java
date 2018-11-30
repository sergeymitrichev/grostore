package ru.ftob.grostore.rest.webmodel;

public class GuiProductInStock extends GuiAbstractNamedEntity {

    private String shelf;

    private GuiProductSimple product;

    public GuiProductInStock() {
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public GuiProductSimple getProduct() {
        return product;
    }

    public void setProduct(GuiProductSimple product) {
        this.product = product;
    }
}
