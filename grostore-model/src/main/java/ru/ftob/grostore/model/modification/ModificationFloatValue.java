package ru.ftob.grostore.model.modification;

import ru.ftob.grostore.model.base.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "modification_float_value")
public class ModificationFloatValue extends AbstractBaseEntity {

    @Column(name = "value")
    @NotNull(message = "Modification value must not be null")
    private Float value;

    @ManyToOne
    @JoinColumn(name = "modification_float_id")
    @NotNull(message = "Modification float must not be null")
    private ModificationFloat modificationFloat;

    public ModificationFloatValue() {
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public ModificationFloat getModificationFloat() {
        return modificationFloat;
    }

    public void setModificationFloat(ModificationFloat modificationFloat) {
        this.modificationFloat = modificationFloat;
    }
}
