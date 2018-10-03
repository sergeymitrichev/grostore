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

    @Column(name = "status")
    private ScheduledTaskConfigStatus status;

    @Column(name = "type")
    private ScheduledTaskConfigType type;

    @ElementCollection
    @CollectionTable(name = "scheduled_task_config_url", joinColumns = @JoinColumn(name = "scheduled_task_config_id"))
    private List<String> url;

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

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public ScheduledTaskConfigStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduledTaskConfigStatus status) {
        this.status = status;
    }

    public ScheduleTaskConfigType getType() {
        return type;
    }

    public void setType(ScheduleTaskConfigType type) {
        this.type = type;
    }
}
