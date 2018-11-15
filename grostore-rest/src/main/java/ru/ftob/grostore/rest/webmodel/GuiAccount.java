package ru.ftob.grostore.rest.webmodel;

import java.time.LocalDateTime;

public class GuiAccount {
    private int id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime visited;


    public GuiAccount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public LocalDateTime getVisited() {
        return visited;
    }

    public void setVisited(LocalDateTime visited) {
        this.visited = visited;
    }
}
