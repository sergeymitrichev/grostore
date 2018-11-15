package ru.ftob.grostore.scheduler;

import ru.ftob.grostore.model.product.PriceType;

public class MetroPrice {
    private Integer value;
    private PriceType type;
    private Integer conditionValue;

    public MetroPrice() {
    }

    public MetroPrice(PriceType type, Integer value) {
        this.value = value;
        this.type = type;
    }

    public MetroPrice(PriceType type, Integer value, Integer conditionValue) {
        this.value = value;
        this.type = type;
        this.conditionValue = conditionValue;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public PriceType getType() {
        return type;
    }

    public void setType(PriceType type) {
        this.type = type;
    }

    public Integer getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(Integer conditionValue) {
        this.conditionValue = conditionValue;
    }


    @Override
    public String toString() {
        return "MetroPrice{" +
                "value=" + value +
                ", type=" + type +
                ", conditionValue=" + conditionValue +
                '}';
    }
}
