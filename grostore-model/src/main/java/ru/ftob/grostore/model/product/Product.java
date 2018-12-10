package ru.ftob.grostore.model.product;

import ru.ftob.grostore.model.base.AbstractPublishedEntity;
import ru.ftob.grostore.model.productlist.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = "sku", name = "product_unique_sku_idx")})
public class Product extends AbstractPublishedEntity {

    @Column(name = "sku")
    @Size(min = 4, max = 56)
    @NotNull(message = "Product SKU must not be null")
    private String sku;

    @Column(name = "unit")
    @Size(max = 56)
    private String unit;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @NotNull(message = "Product category list must not be null")
    private Set<Category> categories = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "price", joinColumns = @JoinColumn(name = "product_id"))
    @NotNull(message = "Product prices must not be null")
    private List<Price> prices;

//    @Column(name = "image")
    @Transient
    private String image;

    public Product() {
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "sku='" + sku + '\'' +
                ", unit='" + unit + '\'' +
                ", categories=" + categories +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Product product = (Product) o;

        return sku.equals(product.sku);
    }

    @Override
    public int hashCode() {
        int result = 17;
        for(int i = 0; i < sku.length(); i++) {
            result += sku.charAt(i);
        }
        return result;
    }
}
