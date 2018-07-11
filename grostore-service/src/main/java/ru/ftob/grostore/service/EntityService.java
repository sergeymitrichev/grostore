package ru.ftob.grostore.service;

import java.util.List;

public interface EntityService<T> {

    T get(int id) throws ClassNotFoundException;

    T create(T entity);

    void update(T entity);

    void delete(int id) throws ClassNotFoundException;

    List<T> getAll();
}
