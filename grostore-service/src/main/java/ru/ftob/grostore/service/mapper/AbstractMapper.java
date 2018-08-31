package ru.ftob.grostore.service.mapper;

import ru.ftob.grostore.service.util.exception.ConfigurationException;

public abstract class AbstractMapper<T, R> {
  public abstract R map(T t, R r) throws ConfigurationException;
}
