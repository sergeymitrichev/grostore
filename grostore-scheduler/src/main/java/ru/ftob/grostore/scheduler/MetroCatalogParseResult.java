package ru.ftob.grostore.scheduler;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MetroCatalogParseResult {
    private Boolean success;
    private List<MetroProductParseResult> items = new ArrayList<>();

    @JsonProperty("data")
    private void unpackItems(Map<String, Object> data) {
        ((List<String>) data.get("items")).forEach(htmlItem -> {
            this.items.add(new MetroProductParseResult(htmlItem));
        });
    }

    public MetroCatalogParseResult() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<MetroProductParseResult> getItems() {
        return items;
    }

    public void setItems(List<MetroProductParseResult> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "MetroCatalogParseResult{" +
                "success=" + success +
                ", items=" + items +
                '}';
    }
}
