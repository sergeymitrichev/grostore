package ru.ftob.grostore.model.base;

import ru.ftob.grostore.model.utils.TextUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractPublishedEntity<T extends AbstractEntityImage> extends AbstractDescribedEntity<T> {

    @Size(max = 100)
    @Column(name = "meta_title")
    private String metaTitle;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "hgu")
    private String hgu;

    @Size(max = 255)
    @Column(name = "meta_description")
    private String metaDescription;

    @Size(max = 255)
    @Column(name = "meta_keywords")
    private String metaKeywords;

    @Transient
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "url", column = @Column(name = "meta_image_url")),
//            @AttributeOverride(name = "alt", column = @Column(name = "meta_image_alt")),
//            @AttributeOverride(name = "title", column = @Column(name = "meta_image_title"))
//    })
//    @LazyCollection(LazyCollectionOption.TRUE)
    private AbstractEntityImage metaImage;

    public AbstractPublishedEntity() {
    }

    public AbstractEntityImage getMetaImage() {
        return metaImage;
    }

    public void setMetaImage(AbstractEntityImage metaImage) {
        this.metaImage = metaImage;
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



    @Override
    public String toString() {
        return "AbstractPublishedEntity{" +
                "title='" + metaTitle + '\'' +
                ", hgu='" + hgu + '\'' +
                ", metaDescription='" + metaDescription + '\'' +
                ", metaKeywords='" + metaKeywords + '\'' +
                "} " + super.toString();
    }

    @PrePersist
    public void generateUrlFromName() {
        if (null != getName() && hgu == null) {
            this.setHgu(TextUtils.transliterateForUrl(getName()));
        }
    }
}
