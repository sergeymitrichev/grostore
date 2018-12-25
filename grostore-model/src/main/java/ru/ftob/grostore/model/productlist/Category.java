package ru.ftob.grostore.model.productlist;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.ftob.grostore.model.base.AbstractPublishedEntity;
import ru.ftob.grostore.model.image.CategoryImage;
import ru.ftob.grostore.model.modification.ModificationFloat;
import ru.ftob.grostore.model.modification.ModificationString;
import ru.ftob.grostore.model.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category", uniqueConstraints = {@UniqueConstraint(columnNames = "forename", name = "category_unique_name_idx")})
@NamedEntityGraph(name = "Category.detail",
        attributeNodes = {
                @NamedAttributeNode("products"),
                @NamedAttributeNode("floatModifications"),
                @NamedAttributeNode("stringModifications")
        }
)
public class Category extends AbstractPublishedEntity<CategoryImage> {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.DETACH, fetch = FetchType.EAGER, orphanRemoval = true)
    private final Set<Category> children = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_float_modification",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "modification_float_id"))
    private List<ModificationFloat> floatModifications = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_string_modification",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "modification_string_id"))
    private List<ModificationString> stringModifications = new ArrayList<>();

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public List<ModificationFloat> getFloatModifications() {
        return floatModifications;
    }

    public void setFloatModifications(List<ModificationFloat> floatModifications) {
        this.floatModifications = floatModifications;
    }

    public void addFloatModification(ModificationFloat modification) {
        floatModifications.add(modification);
    }

    public List<ModificationString> getStringModifications() {
        return stringModifications;
    }

    public void setStringModifications(List<ModificationString> stringModifications) {
        this.stringModifications = stringModifications;
    }

    public void addFloatModification(ModificationString modification) {
        stringModifications.add(modification);
    }
}
