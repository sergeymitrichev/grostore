package ru.ftob.grostore.model.modification;

import ru.ftob.grostore.model.base.AbstractNamedEntity;
import ru.ftob.grostore.model.productlist.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "modification", uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "forename",
                        name = "modification_unique_forename_idx")})
public class Modification extends AbstractNamedEntity {

    @Column(name = "unit")
    @Size(max = 56)
    private String unit;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "modification_value", joinColumns = @JoinColumn(name = "modification_id"))
    @NotNull(message = "Modification values list must not be null")
    private List<ModificationValue> values;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_modification",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "modification_id"))
    private List<Category> categories;

    public Modification() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<ModificationValue> getValues() {
        return values;
    }

    public void setValues(List<ModificationValue> values) {
        this.values = values;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
