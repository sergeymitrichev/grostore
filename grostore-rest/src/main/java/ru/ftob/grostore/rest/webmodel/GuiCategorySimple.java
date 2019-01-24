package ru.ftob.grostore.rest.webmodel;

import java.util.List;

public class GuiCategorySimple {

    private Integer id;
    private List<GuiCategorySimple> children;
    private List<GuiImage> images;
    private String description;
    private String name;
    private Integer entriesCount;

    public GuiCategorySimple() {
    }

    public List<GuiCategorySimple> getChildren() {
        return children;
    }

    public void setChildren(List<GuiCategorySimple> children) {
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

    public GuiCategorySimple(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GuiImage> getImages() {
        return images;
    }

    public void setImages(List<GuiImage> images) {
        this.images = images;
    }
}
