package ru.ftob.grostore.model.base;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractEntityImage<T extends AbstractDescribedEntity> extends AbstractBaseEntity {

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

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = false)
    @JoinColumn(name = "entity_id")
    @NotNull(message = "Image entity must not be null")
    private T entity;

    public AbstractEntityImage() {
    }

    public AbstractEntityImage(
            @Size(max = 255) @NotNull(message = "Image URL must not be null") String url,
            @NotNull(message = "Image entity must not be null") T entity
    ) {
        this.url = url;
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
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
