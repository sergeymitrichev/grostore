package ru.ftob.grostore.ucoz.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UcozOrder {

    private String order_id;

    private String order_nom;

    public UcozOrder() {
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_nom() {
        return order_nom;
    }

    public void setOrder_nom(String order_nom) {
        this.order_nom = order_nom;
    }

    @Override
    public String toString() {
        return "UcozOrder{" +
                "order_id='" + order_id + '\'' +
                ", order_nom='" + order_nom + '\'' +
                '}';
    }
}
