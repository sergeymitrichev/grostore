package ru.ftob.grostore.model;

import java.time.LocalDateTime;

public class AbstractBaseEntity {

    private int id;

    private LocalDateTime created;

    private LocalDateTime updated;

    private User createdBy;

    private User updatedBy;

    public AbstractBaseEntity(int id) {
        this(id, LocalDateTime.now(), null, null, null);
    }

    public AbstractBaseEntity(int id, LocalDateTime created, LocalDateTime updated, User createdBy, User updatedBy) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public AbstractBaseEntity() {
    }

    public boolean isNew() {
        if (id == 0) {
            return true;
        }
        return false;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }
}
