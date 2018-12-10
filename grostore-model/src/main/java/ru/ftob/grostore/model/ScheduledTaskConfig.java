package ru.ftob.grostore.model;

import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.model.base.AbstractNamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "scheduled_task_config", uniqueConstraints = {@UniqueConstraint(columnNames = "forename", name = "scheduled_task_config_unique_name_idx")})
public class ScheduledTaskConfig extends AbstractNamedEntity {

    @Column(name = "periodic", columnDefinition = "boolean default false")
    private boolean periodic;

    @Column(name = "delay", columnDefinition = "bigint default 0")
    private long delay;

    @Column(name = "status", columnDefinition = "int default 0")
    private ScheduledTaskConfigStatus status;

    @Column(name = "type")
    @NotNull(message = "Scheduled task config must have a type")
    private ScheduledTaskConfigType type;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "scheduled_task_config_url", joinColumns = @JoinColumn(name = "scheduled_task_config_id"))
    private List<ScheduledTaskConfigUrl> url;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="account_id")
    private Account productsCreator;

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

    public List<ScheduledTaskConfigUrl> getUrl() {
        return url;
    }

    public void setUrl(List<ScheduledTaskConfigUrl> url) {
        this.url = url;
    }

    public ScheduledTaskConfigStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduledTaskConfigStatus status) {
        this.status = status;
    }

    public ScheduledTaskConfigType getType() {
        return type;
    }

    public void setType(ScheduledTaskConfigType type) {
        this.type = type;
    }

    public Account getProductsCreator() {
        return productsCreator;
    }

    public void setProductsCreator(Account productsCreator) {
        this.productsCreator = productsCreator;
    }
}
