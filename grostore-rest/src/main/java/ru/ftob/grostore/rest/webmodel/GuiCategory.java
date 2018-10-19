package ru.ftob.grostore.rest.webmodel;

import java.util.List;

public class GuiCategory {

    private Integer id;
    private List<GuiCategory> children;
    private String description;
    private String name;
    private Integer entriesCount;

    public GuiCategory() {
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

    public Integer getEntriesCount() {
        return entriesCount;
    }

    public void setEntriesCount(Integer entriesCount) {
        this.entriesCount = entriesCount;
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
}
