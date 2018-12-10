package ru.ftob.grostore.rest.webmodel;

import ru.ftob.grostore.model.product.PriceRuleRowType;

public class GuiPriceRuleRow {
    private Integer from;
    private Integer to;
    private PriceRuleRowType type;
    private Integer value;

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
}
