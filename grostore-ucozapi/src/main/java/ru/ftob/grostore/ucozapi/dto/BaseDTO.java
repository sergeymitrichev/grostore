package ru.ftob.grostore.ucozapi.dto;

import ru.ftob.grostore.model.AbstractEntity;

public abstract class BaseDTO<T extends AbstractEntity> {

    private int id;

    public void transform(T t) {
        id = t.getId();
    }

    public T untransform(T t) {
        t.setId(getId());
        return t;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
