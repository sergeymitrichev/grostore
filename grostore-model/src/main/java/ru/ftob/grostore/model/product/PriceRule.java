package ru.ftob.grostore.model.product;

import org.hibernate.annotations.BatchSize;
import ru.ftob.grostore.model.base.AbstractNamedEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "price_rule")
public class PriceRule extends AbstractNamedEntity {

    @OneToMany(targetEntity = PriceRuleRow.class,
            mappedBy = "priceRule",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<PriceRuleRow> rows = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "price_type")
    @CollectionTable(name = "price_rule_types", joinColumns = @JoinColumn(name = "price_rule_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<PriceType> types = new HashSet<>();

    public List<PriceRuleRow> getRows() {
        return rows;
    }

    public void setRows(List<PriceRuleRow> rows) {
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
