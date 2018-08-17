package ru.ftob.grostore.model.base;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractPublishedEntity extends AbstractDescribedEntity {

    @NotBlank
    @Size(min = 10, max = 100)
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank
    @Size(min = 1, max = 40)
    @Column(name = "url", nullable = false)
    private String url;

    @Size(max = 256)
    @Column(name = "meta_description")
    private String metaDescription;

    @Size(max = 256)
    @Column(name = "meta_keywords")
    private String metaKeywords;

    @Column(name = "meta_image_index")
    private Integer metaImageIndex;

    public AbstractPublishedEntity() {
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

    @Override
    public String toString() {
        return "AbstractPublishedEntity{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", metaDescription='" + metaDescription + '\'' +
                ", metaKeywords='" + metaKeywords + '\'' +
                ", metaImageIndex=" + metaImageIndex +
                "} " + super.toString();
    }
}
