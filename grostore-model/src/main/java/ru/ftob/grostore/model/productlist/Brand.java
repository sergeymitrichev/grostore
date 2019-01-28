package ru.ftob.grostore.model.productlist;

import ru.ftob.grostore.model.base.AbstractPublishedEntity;
import ru.ftob.grostore.model.image.BrandImage;
import ru.ftob.grostore.model.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brand", uniqueConstraints = {@UniqueConstraint(columnNames = "forename", name = "brand_unique_name_idx")})
public class Brand extends AbstractPublishedEntity<BrandImage> {

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    @OrderBy("forename")
    private Set<Product> products = new HashSet<>();

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    public Brand() {
    }

    public Brand(@NotBlank @Size(min = 2, max = 100) String name) {
        super(name);
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "products=" + products +
                "} " + super.toString();
    }
}
