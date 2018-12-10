package ru.ftob.grostore.scheduler.task.to;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.scheduler.task.deserializer.MetroPageDeserializer;

import java.util.List;

@JsonDeserialize(using = MetroPageDeserializer.class)
public class MetroPage {

    private Boolean success;
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

    @Override
    public String toString() {
        return "MetroPage{" +
                "success=" + success +
                '}';
    }
}
