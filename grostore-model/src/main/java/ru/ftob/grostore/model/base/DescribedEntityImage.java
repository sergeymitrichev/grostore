package ru.ftob.grostore.model.base;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class DescribedEntityImage {

    @Column(name = "url")
    @Size(max = 255)
    @NotNull(message = "Image URL must not be null")
    private String url;

    @Column(name = "alt")
    @Size(max = 255)
    private String alt;

    @Column(name = "title")
    @Size(max = 255)
    private String title;

    public DescribedEntityImage() {
    }

    public DescribedEntityImage(@Size(max = 255) @NotNull(message = "Image URL must not be null") String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
