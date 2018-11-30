package ru.ftob.grostore.ucoz.snapshot;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ucoz_products_snapshot")
public class SnapshotProduct {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "forename")
    private String name;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="cat_id")
    private SnapshotCategory category;

    @Column(name = "sku")
    private String sku;

    @Column(name = "price_in")
    private Integer priceIn;

    @Column(name = "stock")
    private String stock;

    @Column(name = "hide")
    private Boolean hide = false;

    public SnapshotProduct() {
    }

    public SnapshotProduct(Integer id, String name, SnapshotCategory category, String sku, Integer priceIn, String stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.sku = sku;
        this.priceIn = priceIn;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SnapshotCategory getCategory() {
        return category;
    }

    public void setCategory(SnapshotCategory category) {
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(Integer priceIn) {
        this.priceIn = priceIn;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Boolean getHide() {
        return hide;
    }

    public void setHide(Boolean hide) {
        this.hide = hide;
    }

    @Override
    public String toString() {
        return "SnapshotProduct{" +
                "id=" + id +
                ", name=" + name +
                ", category=" + category +
                ", sku='" + sku + '\'' +
                ", priceIn=" + priceIn +
                ", stock='" + stock + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnapshotProduct that = (SnapshotProduct) o;
        return Objects.equals(sku, that.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
