package ru.ftob.grostore.model.base;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractDescribedEntity<T extends AbstractEntityImage> extends AbstractNamedEntity {

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "image", joinColumns = @JoinColumn(name = "entity_id"))
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "entity_id")
    @OneToMany(
            mappedBy = "entity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<T> images = new HashSet<>();

    @Column(name = "brief")
    @Size(max = 255)
    private String brief;

    @Column(name = "description")
    @Size(max = 4000)
    private String description;

    public AbstractDescribedEntity() {
    }

    public Set<T> getImages() {
        return images;
    }

    public void addImage(T image) {
        images.add(image);
    }

    public void setImages(Set<T> images) {
        this.images = images;
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
        return "AbstractDescribedEntity{" +
                "images=" + images +
                ", brief='" + brief + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }
}
