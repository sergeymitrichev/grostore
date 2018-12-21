package ru.ftob.grostore.model.modification;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "modification_float_value")
public class ModificationFloatValue extends ModificationValue {

    @Column(name = "value")
    @NotNull(message = "Modification value must not be null")
    private Float value;

    public ModificationFloatValue() {
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }
}
