package ru.ftob.grostore.model.productlist;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.ftob.grostore.model.base.AbstractPublishedEntity;
import ru.ftob.grostore.model.image.CategoryImage;
import ru.ftob.grostore.model.modification.Modification;
import ru.ftob.grostore.model.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category", uniqueConstraints = {@UniqueConstraint(columnNames = "forename", name = "category_unique_name_idx")})
public class Category extends AbstractPublishedEntity<CategoryImage> {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.DETACH, fetch = FetchType.EAGER, orphanRemoval = true)
    private final Set<Category> children = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_modification",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "modification_id"))
    private List<Modification> modifications = new ArrayList<>();

//    private Integer orderSequence;

    public Category() {
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children.clear();
        this.children.addAll(children);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Modification> getModifications() {
        return modifications;
    }

    public void setModifications(List<Modification> modifications) {
        this.modifications = modifications;
    }

    public void addModifications(Modification modification) {
        modifications.add(modification);
    }
}
