package ru.ftob.grostore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.Collection;
import java.util.Optional;

public interface BaseService<T, ID> {

    T create(T t);

    T update(T t);

    Collection<T> updateAll(Collection<T> t);

    Optional<T> get(ID id) throws NotFoundException;

    Page<T> getAll(Pageable pageable);

    Collection<T> getAll();

    void delete(ID id) throws NotFoundException;

    void deleteAll(Collection<T> t);

}
