package ru.ftob.grostore.ucoz.repository;

import ru.ftob.grostore.ucoz.exception.UApiRequestException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

interface ApiBaseRepository<T> {

    public T save(T t) throws IOException, ExecutionException, InterruptedException, UApiRequestException;

    public boolean delete(int id);

    public T get(String id) throws IOException;

    public List<T> getAll() throws IOException; // TODO add uAPI parameters

}
