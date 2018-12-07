package ru.ftob.grostore.rest.webmodel;

import ru.ftob.grostore.model.product.PriceType;

import java.util.Set;

public class GuiPriceRule {
    private Set<GuiPriceRuleRow> rows;
    private Set<PriceType> types;

    public Set<GuiPriceRuleRow> getRows() {
        return rows;
    }

    public void setRows(Set<GuiPriceRuleRow> rows) {
        this.rows = rows;
    }

    public Set<PriceType> getTypes() {
        return types;
    }

    public void setTypes(Set<PriceType> types) {
        this.types = types;
    }
}
