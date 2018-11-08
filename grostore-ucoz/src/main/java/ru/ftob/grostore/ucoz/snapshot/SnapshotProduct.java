package ru.ftob.grostore.ucoz.snapshot;

import javax.persistence.*;

@Entity
@Table(name = "ucoz_products_snapshot")
public class SnapshotProduct {

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="cat_id")
    private SnapshotCategory category;

    @Column(name = "sku")
    private String sku;

    @Column(name = "price_in")
    private Integer priceIn;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="worker_id")
    private SnapshotWorker worker;

    public SnapshotProduct() {
    }

    public SnapshotProduct(Integer id, SnapshotCategory category, String sku, Integer priceIn, SnapshotWorker worker) {
        this.id = id;
        this.category = category;
        this.sku = sku;
        this.priceIn = priceIn;
        this.worker = worker;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public SnapshotWorker getWorker() {
        return worker;
    }

    public void setWorker(SnapshotWorker worker) {
        this.worker = worker;
    }

    @Override
    public String toString() {
        return "SnapshotProduct{" +
                "id=" + id +
                ", category=" + category +
                ", sku='" + sku + '\'' +
                ", priceIn=" + priceIn +
                ", worker='" + worker + '\'' +
                '}';
    }
}
