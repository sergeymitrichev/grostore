package ru.ftob.grostore.rest.webmodel;

public class GuiNodeCategoryPojo {
    private Integer id;
    private String name;
    private String description;
    private GuiNodeCategoryPojo parent;

    public GuiNodeCategoryPojo() {
    }

    public GuiNodeCategoryPojo(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GuiNodeCategoryPojo getParent() {
        return parent;
    }

    public void setParent(GuiNodeCategoryPojo parent) {
        this.parent = parent;
    }
}
