package ru.ftob.grostore.model.product;

import ru.ftob.grostore.model.base.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "price_rule_row")
public class PriceRuleRow extends AbstractBaseEntity {

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="price_rule_id")
    @NotNull
    private PriceRule priceRule;

    @Column(name = "value_from", columnDefinition = "bigint default 0")
    @NotNull
    @Min(0)
    private Integer from;

    @Column(name = "value_to", columnDefinition = "bigint default 0")
    @NotNull
    @Min(0)
    private Integer to;

    @Column(name = "rule_type", columnDefinition = "int default 0")
    @Enumerated(EnumType.ORDINAL)
    private PriceRuleRowType type;

    @Column(name = "value", columnDefinition = "bigint default 0")
    @NotNull
    @Min(0)
    private Integer value;

    public PriceRule getPriceRule() {
        return priceRule;
    }

    public void setPriceRule(PriceRule priceRule) {
        this.priceRule = priceRule;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public PriceRuleRowType getType() {
        return type;
    }

    public void setType(PriceRuleRowType type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "PriceRuleRow{" +
                "from=" + from +
                ", to=" + to +
                ", type=" + type +
                ", value=" + value +
                "} " + super.toString();
    }
}
