package ru.ftob.grostore.model.modification;

import ru.ftob.grostore.model.base.AbstractNamedEntity;
import ru.ftob.grostore.model.productlist.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "modification_float", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = "forename",
                name = "modification_float_unique_forename_idx")})
public class ModificationFloat extends AbstractNamedEntity {

    @Column(name = "unit")
    @Size(max = 56)
    private String unit;

    @NotNull(message = "Modification float values list must not be null")
    @OneToMany(mappedBy = "modificationFloat", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ModificationFloatValue> values = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_float_modification",
            joinColumns = @JoinColumn(name = "modification_float_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public ModificationFloat() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<ModificationFloatValue> getValues() {
        return values;
    }

    public void setValues(List<ModificationFloatValue> values) {
        this.values = values;
    }

    public void addValue(ModificationFloatValue value) {
        values.add(value);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
