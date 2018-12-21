package ru.ftob.grostore.model.modification;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "modification_string_value")
public class ModificationStringValue extends ModificationValue {

    @Column(name = "value")
    @Size(max = 255)
    @NotNull(message = "Modification value must not be null")
    private String value;


    public ModificationStringValue() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
