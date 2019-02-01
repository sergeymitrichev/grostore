package ru.ftob.grostore.rest.webmodel;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.ftob.grostore.model.productlist.Category;

import java.util.List;

@JsonSerialize(using = GuiNodeCategorySerializer.class)
public class GuiNodeCategory {
    private Integer id;
    private Category parent;
    private List<GuiNodeCategory> children;
    private List<GuiImage> images;
    private String name;
    private String description;
    private Object data;
    private Integer entriesCount;
    private String hgu;

    public GuiNodeCategory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<GuiNodeCategory> getChildren() {
        return children;
    }

    public void setChildren(List<GuiNodeCategory> children) {
        this.children = children;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
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

    public Integer getEntriesCount() {
        return entriesCount;
    }

    public void setEntriesCount(Integer entriesCount) {
        this.entriesCount = entriesCount;
    }

    public List<GuiImage> getImages() {
        return images;
    }

    public void setImages(List<GuiImage> images) {
        this.images = images;
    }

    public String getHgu() {
        return hgu;
    }

    public void setHgu(String hgu) {
        this.hgu = hgu;
    }
}
