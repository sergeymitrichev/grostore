package ru.ftob.grostore.ucoz.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UcozProduct {

    @JsonProperty("entry_id")
    private String id;

    @JsonProperty("entry_art_no")
    private String sku;

    @JsonProperty("entry_title")
    private String name;

    @JsonProperty("entry_price_in")
    @JsonDeserialize(converter = UcozPriceConverter.class)
    private Integer priceIn;

    @JsonProperty("entry_cat")
    @JsonDeserialize(converter = UcozCategoryConverter.class)
    private UcozCategory category;

    @JsonProperty("hide")
    private Integer hide = 0;

    public UcozProduct() {
    }

    public UcozProduct(String id, String sku, String name, Integer priceIn) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.priceIn = priceIn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public UcozCategory getCategory() {
        return category;
    }

    public void setCategory(UcozCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHide() {
        return hide;
    }

    public void setHide(Integer hide) {
        this.hide = hide;
    }


    @Override
    public String toString() {
        return "UcozProduct{" +
                "id='" + id + '\'' +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", priceIn=" + priceIn +
                ", category=" + category +
                ", hide=" + hide +
                '}';
    }
}
