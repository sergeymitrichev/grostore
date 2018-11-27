package ru.ftob.grostore.model.stock;

import ru.ftob.grostore.model.base.AbstractBaseEntity;
import ru.ftob.grostore.model.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(
        name = "product_in_stock",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"stock_id", "product_id", "shelf"},
                name = "product_in_stock_unique_shelf_idx")
        })
public class ProductInStock extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "shelf")
    @NotBlank
    @Size(min = 1, max = 16)
    private String shelf;

    public ProductInStock() {
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    @Override
    public String toString() {
        return "ProductInStock{" +
                "stock=" + stock +
                ", product=" + product +
                ", shelf='" + shelf + '\'' +
                "} " + super.toString();
    }
}
