package ru.ftob.grostore.model.product;

public enum ProductImportFieldType {
    _IGNORE("ignore"),
    ID("id"),
    PRICE_IN("priceIn"),
    SKU("sku"),
    NAME("name"),
    CATEGORIES("categories"),
    TITLE("title"),
    URL("url"),
    META_DESCRIPTION("metaDescription"),
    META_KEYWORDS("metaKeywords"),
    META_IMAGE("metaImageIndex"),
    IMAGES("images"),
    BRIEF("brief"),
    STOCK("stock"),
    DESCRIPTION("description");

    private String value;
    ProductImportFieldType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
