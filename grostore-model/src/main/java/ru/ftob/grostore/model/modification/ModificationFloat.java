package ru.ftob.grostore.model.modification;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "modification_float", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = "forename",
                name = "modification_float_unique_forename_idx")})
public class ModificationFloat extends Modification {

    @Column(name = "unit")
    @Size(max = 56)
    private String unit;

    @NotNull(message = "Modification float values list must not be null")
    @OneToMany(mappedBy = "modification", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ModificationFloatValue> values;

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
}
