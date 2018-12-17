package ru.ftob.grostore.model.base;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Access(AccessType.FIELD)
@NamedEntityGraph()
public class AbstractDescribedEntity extends AbstractNamedEntity {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "image", joinColumns = @JoinColumn(name = "entity_id"))
    private Set<DescribedEntityImage> images = new HashSet<>();

    @Column(name = "brief")
    @Size(max = 255)
    private String brief;

    @Column(name = "description")
    @Size(max = 4000)
    private String description;

    public AbstractDescribedEntity() {
    }

    public Set<DescribedEntityImage> getImages() {
        return images;
    }

    public void addImage(DescribedEntityImage image) {
        images.add(image);
    }

    public void setImages(Set<DescribedEntityImage> images) {
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
