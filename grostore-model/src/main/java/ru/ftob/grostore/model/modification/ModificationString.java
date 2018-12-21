package ru.ftob.grostore.model.modification;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "modification_string", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = "forename",
                name = "modification_string_unique_forename_idx")})
public class ModificationString extends Modification {

    @NotNull(message = "Modification string values list must not be null")
    @OneToMany(mappedBy = "modification", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ModificationStringValue> values;

    public ModificationString() {
    }

    public List<ModificationStringValue> getValues() {
        return values;
    }

    public void setValues(List<ModificationStringValue> values) {
        this.values = values;
    }
}
