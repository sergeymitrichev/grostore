package ru.ftob.grostore.rest.webmodel;

public class GuiCategory {
    private String name;
    private Integer entriesCount;

    public GuiCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
