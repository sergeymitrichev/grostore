package ru.ftob.grostore.scheduler.task.converter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.ftob.grostore.model.product.Product;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = MetroPageDeserializer.class)
public class MetroPage {
    private Boolean success;
    private List<MetroProduct> items = new ArrayList<>();
//    @JsonProperty("data")
//    private void unpackItems(Map<String, Object> data) {
//        ((List<String>) data.get("items")).forEach(htmlItem -> {
//            this.items.add(new MetroProduct(htmlItem));
//        });
//    }

    private List<Product> products;

    public MetroPage() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<MetroProduct> getItems() {
        return items;
    }

    public void setItems(List<MetroProduct> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "MetroPage{" +
                "success=" + success +
                ", items=" + items +
                '}';
    }
}
