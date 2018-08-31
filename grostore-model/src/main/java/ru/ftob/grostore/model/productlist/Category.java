package ru.ftob.grostore.model.productlist;

import ru.ftob.grostore.model.base.AbstractNamedEntity;
import ru.ftob.grostore.model.base.AbstractPublishedEntity;
import ru.ftob.grostore.model.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category extends AbstractNamedEntity {

    //TODO Could not determine type for: ru.ftob.grostore.model.base.AbstractPublishedEntity, at table: category, for columns: [org.hibernate.mapping.Column(article)]
    @Transient
    private AbstractPublishedEntity article;

    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    public Category() {
    }

    public AbstractPublishedEntity getArticle() {
        return article;
    }

    public void setArticle(AbstractPublishedEntity article) {
        this.article = article;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "article=" + article +
                "} " + super.toString();
    }
}
