package ru.ftob.grostore.ucoz.repository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

interface ApiBaseRepository<T> {

    public T save(T t) throws IOException, ExecutionException, InterruptedException;

    public boolean delete(int id);

    public T get(String id) throws IOException;

    public List<T> getAll() throws IOException; // TODO add uAPI parameters

}
