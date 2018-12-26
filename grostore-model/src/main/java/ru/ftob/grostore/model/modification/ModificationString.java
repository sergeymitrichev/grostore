package ru.ftob.grostore.model.modification;

import ru.ftob.grostore.model.base.AbstractNamedEntity;
import ru.ftob.grostore.model.productlist.Category;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "modification_string", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = "forename",
                name = "modification_string_unique_forename_idx")})
public class ModificationString extends AbstractNamedEntity {

    @NotNull(message = "Modification string values list must not be null")
    @OneToMany(mappedBy = "modificationString", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ModificationStringValue> values;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_string_modification",
            joinColumns = @JoinColumn(name = "modification_string_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public ModificationString() {
    }

    public List<ModificationStringValue> getValues() {
        return values;
    }

    public void setValues(List<ModificationStringValue> values) {
        this.values = values;
    }

    public void addValue(ModificationStringValue value) {
        values.add(value);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
