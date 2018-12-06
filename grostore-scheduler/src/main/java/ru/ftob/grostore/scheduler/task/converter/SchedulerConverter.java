package ru.ftob.grostore.scheduler.task.converter;

import java.util.Collection;

public interface SchedulerConverter<S, T> {
    Collection<T> convert(S source);
}
