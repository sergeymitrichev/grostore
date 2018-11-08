package ru.ftob.grostore.ucoz.snapshot;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ucoz_workers_snapshot")
public class SnapshotWorker {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created")
    private LocalDateTime created;

    public SnapshotWorker() {
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

    @Override
    public String toString() {
        return "SnapshotWorker{" +
                "id='" + id + '\'' +
                ", created=" + created +
                '}';
    }

    @PrePersist
    void createdAt() {
        this.created = LocalDateTime.now();
    }
}
