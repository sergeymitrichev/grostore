package ru.ftob.grostore.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
public class Price {

    @Column(name = "value")
    @NotNull(message = "Price value must not be null")
    private Integer value;

    @Column(name = "type")
    @NotNull(message = "Price type must not be null")
    @Enumerated(EnumType.STRING)
    private PriceType type;

    @Column(name = "condition_value")
    private Integer conditionValue;

    public Price() {
    }

    public Price(@NotNull(message = "Price type must not be null") PriceType type) {
        this.type = type;
    }

    public Price(@NotNull(message = "Price value must not be null") Integer value, @NotNull(message = "Price type must not be null") PriceType type) {
        this.value = value;
        this.type = type;
    }

    public Price(@NotNull(message = "Price value must not be null") Integer value, @NotNull(message = "Price type must not be null") PriceType type, Integer conditionValue) {
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

}
