package ru.ftob.grostore.model.productlist;

import ru.ftob.grostore.model.base.AbstractProductListEntity;
import ru.ftob.grostore.model.base.AbstractPublishedEntity;
import ru.ftob.grostore.model.product.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category extends AbstractProductListEntity {

    //TODO Could not determine type for: ru.ftob.grostore.model.base.AbstractPublishedEntity, at table: category, for columns: [org.hibernate.mapping.Column(article)]
    @Transient
    private AbstractPublishedEntity article;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    public Category() {
    }

    public AbstractPublishedEntity getArticle() {
        return article;
    }

    public void setArticle(AbstractPublishedEntity article) {
        this.article = article;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "article=" + article +
                ", products=" + products +
                "} " + super.toString();
    }
}
