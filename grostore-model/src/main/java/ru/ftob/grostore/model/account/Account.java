package ru.ftob.grostore.model.account;

import ru.ftob.grostore.model.base.AbstractNamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
public class Account extends AbstractNamedEntity {

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    @Email
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "visited", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime visited;

    public Account(String name, String email, String password) {
        super(name);
        this.email = email;
        this.password = password;
    }

    public Account() {
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
