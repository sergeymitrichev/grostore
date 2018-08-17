package ru.ftob.grostore.model.base;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

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

//    @CreatedBy
//    @ManyToOne
//    @JoinColumn(name="created_by", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @NotNull
//    private User createdBy;

//    @ManyToOne
//    @JoinColumn(name="updated_by", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @NotNull
//    private User updatedBy;

    public AbstractBaseEntity() {
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        if (id == null || id == 0) {
            return true;
        }
        return false;
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

//    public User getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(User createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public User getUpdatedBy() {
//        return updatedBy;
//    }
//
//    public void setUpdatedBy(User updatedBy) {
//        this.updatedBy = updatedBy;
//    }

    @Override
    public String toString() {
        return "AbstractBaseEntity{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
//                ", createdBy=" + createdBy +
//                ", updatedBy=" + updatedBy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        //TODO what about created and updated fields?
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return  id == null ? 0 : id;
    }
}
