package ru.ftob.grostore.rest.webmodel;

import java.util.List;
import java.util.Set;

public class GuiProductSimple {
    private Integer id;
    private String name;
    private String sku;
    private String brief;
    private String hgu;
    private String unit;
    private Boolean enabled;
    private List<GuiPrice> prices;
    private Set<GuiImage> images;

    public GuiProductSimple() {
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

    public List<GuiPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<GuiPrice> prices) {
        this.prices = prices;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getHgu() {
        return hgu;
    }

    public void setHgu(String hgu) {
        this.hgu = hgu;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<GuiImage> getImages() {
        return images;
    }

    public void setImages(Set<GuiImage> images) {
        this.images = images;
    }
}
