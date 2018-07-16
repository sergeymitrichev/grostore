package ru.ftob.grostore.model;

import java.time.LocalDateTime;

public class User extends AbstractNamedEntity {

    private String phone;

    private String email;

    private String password;

    private LocalDateTime visited;

    public User(String name, String email, String password) {
        super(name);
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getVisited() {
        return visited;
    }

    public void setVisited(LocalDateTime visited) {
        this.visited = visited;
    }
}
