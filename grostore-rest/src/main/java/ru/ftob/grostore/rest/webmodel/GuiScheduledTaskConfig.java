package ru.ftob.grostore.rest.webmodel;

import java.time.LocalDateTime;
import java.util.List;

public class GuiScheduledTaskConfig {
    private int id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private GuiAccount createdBy;
    private GuiAccount updatedBy;
    private String name;
    private boolean periodic;
    private int delay;
    private boolean cancelled;
    private List<String> urlList;

    public GuiScheduledTaskConfig() {
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

    public boolean isPeriodic() {
        return periodic;
    }

    public void setPeriodic(boolean periodic) {
        this.periodic = periodic;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }
}
