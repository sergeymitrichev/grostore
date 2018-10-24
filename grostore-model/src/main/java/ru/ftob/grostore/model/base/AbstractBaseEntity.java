package ru.ftob.grostore.model.base;

import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import ru.ftob.grostore.model.account.Account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedDate
    @Column(name = "created", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "updated", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime updated = LocalDateTime.now();

    @CreatedBy
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="created_by")
    private Account createdBy;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="updated_by")
    private Account updatedBy;

    public AbstractBaseEntity() {
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null || id == 0;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AbstractBaseEntity{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
