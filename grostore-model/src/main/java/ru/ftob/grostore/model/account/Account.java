package ru.ftob.grostore.model.account;

import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;
import ru.ftob.grostore.model.base.AbstractNamedEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

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
    //TODO generate password if null
    //@NotNull
    private String password;

    @Column(name = "visited", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime visited;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "account_roles", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "Account{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", visited=" + visited +
                ", roles=" + roles +
                "} " + super.toString();
    }
}
