package ru.ftob.grostore.rest.webmodel;

public class GuiStock extends GuiAbstractNamedEntity {

    private String type;

    public GuiStock() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
