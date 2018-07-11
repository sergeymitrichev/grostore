package ru.ftob.grostore.ucoz.api;

import ru.ftob.grostore.ucoz.to.UcozTO;

import java.util.List;

interface UcozApi<T extends UcozTO> {

    public T save(T t);

    public boolean delete(int id);

    public T get(String id);

    public List<T> getAll(); // TODO add uAPI parameters

}
