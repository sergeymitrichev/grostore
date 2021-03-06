package ru.ftob.grostore.rest.webmodel;

import ru.ftob.grostore.model.account.Role;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class GuiAccount {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime visited;
    private Boolean enabled;
    private Set<Role> roles = new HashSet<>();

    public GuiAccount() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "GuiAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", visited=" + visited +
                ", roles=" + roles +
                '}';
    }
}
