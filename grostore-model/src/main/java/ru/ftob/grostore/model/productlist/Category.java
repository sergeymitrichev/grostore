package ru.ftob.grostore.model.productlist;

import ru.ftob.grostore.model.base.AbstractPublishedEntity;
import ru.ftob.grostore.model.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category extends AbstractPublishedEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.DETACH, fetch = FetchType.EAGER, orphanRemoval = true)
    private final Set<Category> children = new HashSet<>();

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

}
