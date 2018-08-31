package ru.ftob.grostore.persistence;

import ru.ftob.grostore.model.user.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    List<User> getAll();
}
