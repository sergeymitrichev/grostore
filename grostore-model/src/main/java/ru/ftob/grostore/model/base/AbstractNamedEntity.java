package ru.ftob.grostore.model.base;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import ru.ftob.grostore.model.account.Account;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "forename", nullable = false)
    private String name;

    @Column(name = "enabled", columnDefinition = "boolean default true")
    private boolean enabled;

    @CreatedDate
    @Column(name = "created", updatable = false, nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated", columnDefinition = "timestamp")
    private LocalDateTime updated;

    @CreatedBy
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="created_by")
    private Account createdBy;

    @LastModifiedBy
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="updated_by")
    private Account updatedBy;

    @Column(name = "entity_order", columnDefinition = "int4 default 0")
    @Size(max = 255)
    private Integer entityOrder;

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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    public Account getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Account updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getEntityOrder() {
        return entityOrder;
    }

    public void setEntityOrder(Integer entityOrder) {
        this.entityOrder = entityOrder;
    }

    @Override
    public String toString() {
        return "AbstractNamedEntity{" +
                "name='" + name + '\'' +
                ", enabled=" + enabled +
                ", created=" + created +
                ", updated=" + updated +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", entityOrder=" + entityOrder +
                "} " + super.toString();
    }
}
