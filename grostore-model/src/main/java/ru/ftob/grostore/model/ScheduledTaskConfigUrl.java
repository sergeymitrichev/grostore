package ru.ftob.grostore.model;

import ru.ftob.grostore.model.productlist.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
public class ScheduledTaskConfigUrl {

    @Column(name = "link")
    @NotNull(message = "Link must not be null")
    private String link;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    public ScheduledTaskConfigUrl() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
