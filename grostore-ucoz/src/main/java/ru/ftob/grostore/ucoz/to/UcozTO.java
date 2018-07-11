package ru.ftob.grostore.ucoz.to;

import ru.ftob.grostore.model.AbstractEntity;

public abstract class UcozTO<T extends AbstractEntity> {

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
