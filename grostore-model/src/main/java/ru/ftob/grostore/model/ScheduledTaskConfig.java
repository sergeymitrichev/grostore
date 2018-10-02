package ru.ftob.grostore.model;

import ru.ftob.grostore.model.base.AbstractNamedEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "scheduled_task_config")
public class ScheduledTaskConfig extends AbstractNamedEntity {

    @Column(name = "periodic")
    private boolean periodic;

    @Column(name = "delay")
    private long delay;

    @Column(name = "cancelled")
    private boolean cancelled;

    @ElementCollection
    @CollectionTable(name = "scheduled_task_config_url", joinColumns = @JoinColumn(name = "scheduled_task_config_id"))
    private List<String> urlList;

    public ScheduledTaskConfig() {
    }

    public boolean isPeriodic() {
        return periodic;
    }

    public void setPeriodic(boolean periodic) {
        this.periodic = periodic;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }
}
