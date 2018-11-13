package ru.ftob.grostore.rest.webmodel;

import java.time.LocalDateTime;
import java.util.List;

public class GuiScheduledTaskConfig {
    private Integer id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private GuiAccount createdBy;
    private GuiAccount updatedBy;
    private String name;
    private boolean periodic;
    private int delay;
    private List<GuiScheduledTaskConfigUrl> url;
    private String type;
    private String status;

    public GuiScheduledTaskConfig() {
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

    public List<GuiScheduledTaskConfigUrl> getUrl() {
        return url;
    }

    public void setUrl(List<GuiScheduledTaskConfigUrl> url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
