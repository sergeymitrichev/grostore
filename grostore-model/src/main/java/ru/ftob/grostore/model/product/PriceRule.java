package ru.ftob.grostore.model.product;

import org.hibernate.annotations.BatchSize;
import ru.ftob.grostore.model.base.AbstractBaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "price_rule")
public class PriceRule extends AbstractBaseEntity {

    @OneToMany(mappedBy = "priceRule", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<PriceRuleRow> rows = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "price_type")
    @CollectionTable(name = "price_rule_types", joinColumns = @JoinColumn(name = "price_rule_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<PriceType> types = new HashSet<>();

    public Set<PriceRuleRow> getRows() {
        return rows;
    }

    public void setRows(Set<PriceRuleRow> rows) {
        this.rows = rows;
    }

    public void addRow(PriceRuleRow row) {
        rows.add(row);
    }

    public Set<PriceType> getTypes() {
        return types;
    }

    public void setTypes(Set<PriceType> types) {
        this.types = types;
    }

    public void addType(PriceType type) {
        types.add(type);
    }
}
