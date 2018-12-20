package ru.ftob.grostore.model.modification;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

//@MappedSuperclass
//@Access(AccessType.FIELD)
@Embeddable
public abstract class ModificationValue<T> {
    @Column(name = "value")
    @NotNull(message = "Modification value must not be null")
    private T value;

    @ManyToOne
    @JoinColumn(name = "id")
    @NotNull(message = "Modification must not be null")
    private Modification modification;

    public ModificationValue() {
    }

    public ModificationValue(@NotNull(message = "Modification value must not be null") T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Modification getModification() {
        return modification;
    }

    public void setModification(Modification modification) {
        this.modification = modification;
    }

    public void setValue(T value) {
        this.value = value;

    }

}
