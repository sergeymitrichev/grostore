package ru.ftob.grostore.ucoz.api;

import ru.ftob.grostore.ucoz.to.UcozTO;

import java.io.IOException;
import java.util.List;

interface UcozApi<T extends UcozTO> {

    public T save(T t);

    public boolean delete(int id);

    public T get(String id) throws IOException;

    public List<T> getAll() throws IOException; // TODO add uAPI parameters

}
