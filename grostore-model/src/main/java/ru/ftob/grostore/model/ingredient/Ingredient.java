package ru.ftob.grostore.model.ingredient;

import ru.ftob.grostore.model.base.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
        name = "ingredient",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = "forename",
                name = "ingredient_unique_forename_idx")})
public class Ingredient extends AbstractBaseEntity {

    @Column(name = "forename")
    private String name;

    public Ingredient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
