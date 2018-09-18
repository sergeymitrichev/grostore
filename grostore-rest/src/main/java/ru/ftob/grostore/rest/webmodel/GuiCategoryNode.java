package ru.ftob.grostore.rest.webmodel;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GuiCategoryNode {
    private Integer id;
    private List<GuiCategoryNode> children;
    private String description;
    @JsonProperty("title")
    private String name;
    private Integer entriesCount;

    public GuiCategoryNode() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<GuiCategoryNode> getChildren() {
        return children;
    }

    public void setChildren(List<GuiCategoryNode> children) {
        this.children = children;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEntriesCount() {
        return entriesCount;
    }

    public void setEntriesCount(Integer entriesCount) {
        this.entriesCount = entriesCount;
    }
}
