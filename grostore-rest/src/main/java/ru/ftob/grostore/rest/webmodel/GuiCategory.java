package ru.ftob.grostore.rest.webmodel;

import java.util.List;

public class GuiCategory {

    private Integer id;
    private List<GuiCategory> children;
    private String description;
    private String name;
    private List<GuiProductSimple> products;
    private List<GuiModificationString> stringModifications;
    private List<GuiModificationFloat> floatModifications;

    public GuiCategory() {
    }

    public List<GuiProductSimple> getProducts() {
        return products;
    }

    public void setProducts(List<GuiProductSimple> products) {
        this.products = products;
    }

    public List<GuiCategory> getChildren() {
        return children;
    }

    public void setChildren(List<GuiCategory> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GuiCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GuiModificationString> getStringModifications() {
        return stringModifications;
    }

    public void setStringModifications(List<GuiModificationString> stringModifications) {
        this.stringModifications = stringModifications;
    }

    public List<GuiModificationFloat> getFloatModifications() {
        return floatModifications;
    }

    public void setFloatModifications(List<GuiModificationFloat> floatModifications) {
        this.floatModifications = floatModifications;
    }
}
