package ru.ftob.grostore.model.stock;

import ru.ftob.grostore.model.base.AbstractNamedEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "stock",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"forename"},
                name = "stock_unique_name_idx")})
public class Stock extends AbstractNamedEntity {

    @Column(name = "type", columnDefinition = "int default 0")
    private StockType type;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductInStock> products = new ArrayList<>();

    public Stock() {
    }

    public StockType getType() {
        return type;
    }

    public void setType(StockType type) {
        this.type = type;
    }

    public List<ProductInStock> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInStock> products) {
        this.products = products;
    }

    public void addProduct(ProductInStock product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "type=" + type +
                ", products=" + products +
                "} " + super.toString();
    }
}
