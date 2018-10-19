package ru.ftob.grostore.rest.webmodel;

import ru.ftob.grostore.model.product.PriceType;

public class GuiPrice {
    private PriceType type;
    private Integer value;
    private Integer conditionValue;
    private Integer productId;

    public PriceType getType() {
        return type;
    }

    public void setType(PriceType type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(Integer conditionValue) {
        this.conditionValue = conditionValue;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
