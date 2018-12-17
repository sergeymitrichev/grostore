package ru.ftob.grostore.rest.webmodel;

import java.time.LocalDateTime;
import java.util.Set;

public class GuiProduct {

    private Integer id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String name;
    private String brief;
    private String description;
    private String metaDescription;
    private String metaKeywords;
    private String metaTitle;
    private String hgu;
    private String sku;
    private Set<GuiCategorySimple> categories;
    private Set<GuiPrice> prices;
    private Set<GuiImages> images;

    public GuiProduct() {
    }

    public GuiProduct(String name, String sku, Set<GuiCategorySimple> categories) {
        this.name = name;
        this.sku = sku;
        this.categories = categories;
    }

    public Set<GuiImages> getImages() {
        return images;
    }

    public void setImages(Set<GuiImages> images) {
        this.images = images;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Set<GuiCategorySimple> getCategories() {
        return categories;
    }

    public void setCategories(Set<GuiCategorySimple> categories) {
        this.categories = categories;
    }

    public Set<GuiPrice> getPrices() {
        return prices;
    }

    public void setPrices(Set<GuiPrice> prices) {
        this.prices = prices;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getHgu() {
        return hgu;
    }

    public void setHgu(String hgu) {
        this.hgu = hgu;
    }
}
