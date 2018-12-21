package ru.ftob.grostore.model.modification;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//@MappedSuperclass
//@Access(AccessType.FIELD)
@Entity
@Table(name = "modification_value")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ModificationValue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "modification_id")
    @NotNull(message = "Modification must not be null")
    private Modification modification;

    public ModificationValue() {
    }

    public Modification getModification() {
        return modification;
    }

    public void setModification(Modification modification) {
        this.modification = modification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
