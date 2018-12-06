package ru.ftob.grostore.scheduler.task.converter;

import java.util.ArrayList;
import java.util.List;


public class MetroCatalog {
    private Boolean success;
    private List<MetroProduct> items = new ArrayList<>();
    private List<String> data;
//    @JsonProperty("data")
//    private void unpackItems(Map<String, Object> data) {
//        ((List<String>) data.get("items")).forEach(htmlItem -> {
//            this.items.add(new MetroProduct(htmlItem));
//        });
//    }

    public MetroCatalog() {
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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MetroCatalog{" +
                "success=" + success +
                ", items=" + items +
                '}';
    }
}
