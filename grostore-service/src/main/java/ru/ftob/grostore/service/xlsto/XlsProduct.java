package ru.ftob.grostore.service.xlsto;

import com.poiji.annotation.ExcelCellName;

public class XlsProduct {

    @ExcelCellName("ID")
    private Integer id;

    @ExcelCellName("NAME")
    private String name;

    @ExcelCellName("SKU")
    private String sku;

    @ExcelCellName("PRICE_IN")
    private Integer priceIn;

    @ExcelCellName("CATEGORIES")
    private String categoriesString;

    @ExcelCellName("TITLE")
    private String title;

    @ExcelCellName("URL")
    private String url;

    @ExcelCellName("META_DESCRIPTION")
    private String metaDescription;

    @ExcelCellName("META_KEYWORDS")
    private String metaKeywords;

    @ExcelCellName("META_IMAGE")
    private Integer metaImageIndex;

    @ExcelCellName("IMAGES")
    private String imagesString;

    @ExcelCellName("BRIEF")
    private String brief;

    @ExcelCellName("DESCRIPTION")
    private String description;

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

    public Integer getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(Integer priceIn) {
        this.priceIn = priceIn;
    }

    public String getCategoriesString() {
        return categoriesString;
    }

    public void setCategoriesString(String categoriesString) {
        this.categoriesString = categoriesString;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integer getMetaImageIndex() {
        return metaImageIndex;
    }

    public void setMetaImageIndex(Integer metaImageIndex) {
        this.metaImageIndex = metaImageIndex;
    }

    public String getImagesString() {
        return imagesString;
    }

    public void setImagesString(String imagesString) {
        this.imagesString = imagesString;
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

    @Override
    public String toString() {
        return "XlsProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", priceIn=" + priceIn +
                ", categoriesString=" + categoriesString +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", metaDescription='" + metaDescription + '\'' +
                ", metaKeywords='" + metaKeywords + '\'' +
                ", metaImageIndex=" + metaImageIndex +
                ", imagesString=" + imagesString +
                ", brief='" + brief + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
