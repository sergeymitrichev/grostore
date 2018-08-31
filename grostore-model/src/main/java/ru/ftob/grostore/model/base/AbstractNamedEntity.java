package ru.ftob.grostore.model.base;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @NotBlank
//    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    protected AbstractNamedEntity() {

    }

    public AbstractNamedEntity(@NotBlank @Size(min = 2, max = 100) String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AbstractNamedEntity{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
