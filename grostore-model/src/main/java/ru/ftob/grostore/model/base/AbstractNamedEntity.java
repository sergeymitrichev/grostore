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
    @Size(min = 3, max = 100)
    @Column(name = "forename", nullable = false)
    private String name;

    @Column(name = "enabled", columnDefinition = "boolean default true")
    private boolean enabled;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "AbstractNamedEntity{" +
                "name='" + name + '\'' +
                "enabled='" + enabled + '\'' +
                "} " + super.toString();
    }
}
