package ru.ftob.grostore.service.mapper;

import ru.ftob.grostore.service.util.exception.ConfigurationException;

import java.util.function.Supplier;

public abstract class AbstractMapper<T, R> {
  public abstract R map(T t, Supplier<R> func) throws ConfigurationException;
}
