package ru.ftob.grostore.model.base;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractDescribedEntity extends AbstractNamedEntity {

    @Transient
    //TODO Could not determine type for: java.util.List, at table: product, for columns: [org.hibernate.mapping.Column(images)]
    private List<String> images;

    @Column(name = "brief")
    private String brief;

//    @Size(max = 4000)
    @Column(name = "description")
    private String description;

    public AbstractDescribedEntity() {
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
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
