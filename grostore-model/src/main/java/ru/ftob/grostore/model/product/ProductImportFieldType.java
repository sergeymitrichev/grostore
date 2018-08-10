package ru.ftob.grostore.model.product;

public enum ProductImportFieldType {
    ID("id"),
    PRICE_IN("priceIn"),
    SKU("sku"),
    NAME("name");

    private String value;
    ProductImportFieldType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
