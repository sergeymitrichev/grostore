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

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
