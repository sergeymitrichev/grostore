package ru.ftob.grostore.rest.webmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GuiCategory {

    private Integer id;
    @JsonProperty("parent.id")
    private Integer parentId;
    private List<GuiCategory> child;
    private String description;
    private String name;
    private Integer entriesCount;

    public GuiCategory() {
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<GuiCategory> getChild() {
        return child;
    }

    public void setChild(List<GuiCategory> child) {
        this.child = child;
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
