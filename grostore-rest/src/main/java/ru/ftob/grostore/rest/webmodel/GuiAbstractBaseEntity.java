package ru.ftob.grostore.rest.webmodel;

import java.time.LocalDateTime;

public abstract class GuiAbstractBaseEntity {

    private Integer id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private GuiAccount createdBy;
    private GuiAccount updatedBy;

    public GuiAbstractBaseEntity() {
    }

    public Integer getId() {
        return id;
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

    public GuiAccount getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(GuiAccount createdBy) {
        this.createdBy = createdBy;
    }

    public GuiAccount getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(GuiAccount updatedBy) {
        this.updatedBy = updatedBy;
    }
}
