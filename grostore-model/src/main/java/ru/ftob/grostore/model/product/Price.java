package ru.ftob.grostore.model.product;

import ru.ftob.grostore.model.base.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "price")
public class Price extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "value")
    @NotNull(message = "Price value must not be null")
    private Integer value;

    @Column(name = "type")
    @NotNull(message = "Price type must not be null")
    @Enumerated(EnumType.STRING)
    private PriceType type;

    @Column(name = "condition_value")
    private Integer conditionValue;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
