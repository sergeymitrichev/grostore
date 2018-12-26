package ru.ftob.grostore.model.modification;

import ru.ftob.grostore.model.base.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "modification_string_value")
public class ModificationStringValue extends AbstractBaseEntity {

    @Column(name = "value")
    @Size(max = 255)
    @NotNull(message = "Modification value must not be null")
    private String value;

    @ManyToOne
    @JoinColumn(name = "modification_string_id")
    @NotNull(message = "Modification string must not be null")
    private ModificationString modificationString;

    public ModificationStringValue() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ModificationString getModificationString() {
        return modificationString;
    }

    public void setModificationString(ModificationString modificationString) {
        this.modificationString = modificationString;
    }
}
