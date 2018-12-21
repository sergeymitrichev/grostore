package ru.ftob.grostore.model.modification;

import ru.ftob.grostore.model.productlist.Category;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "modification")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Modification {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "forename")
    @Size(max = 255)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_modification",
            joinColumns = @JoinColumn(name = "modification_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public Modification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
