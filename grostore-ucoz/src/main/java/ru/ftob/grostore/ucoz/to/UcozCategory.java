package ru.ftob.grostore.ucoz.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UcozCategory {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    private String parentId;
    private String level;
    private String description;
    private String productCount;
    private String imageUrl;

    public UcozCategory() {
    }

    public UcozCategory(String id, String name, String url, String parentId, String level, String description, String productCount, String imageUrl) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.parentId = parentId;
        this.level = level;
        this.description = description;
        this.productCount = productCount;
        this.imageUrl = imageUrl;
    }

    public UcozCategory(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public String toString() {
        return "UcozCategory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", parentId=" + parentId +
                ", level=" + level +
                ", description='" + description + '\'' +
                ", productCount=" + productCount +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
