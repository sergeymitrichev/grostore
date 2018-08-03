package ru.ftob.grostore.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends AbstractNamedEntity {
    public User() {
    }
}
