package ru.ftob.grostore.ucoz.repository;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

interface ApiBaseRepository<T> {

    public T save(T t) throws IOException;

    public boolean delete(int id);

    public T get(String id) throws IOException;

    public List<T> getAll() throws IOException; // TODO add uAPI parameters

}
