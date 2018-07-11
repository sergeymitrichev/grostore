package ru.ftob.grostore.ucozapi.repository;

import com.github.scribejava.core.model.Verb;
import ru.ftob.grostore.model.User;
import ru.ftob.grostore.persistence.UserRepository;
import ru.ftob.grostore.ucozapi.client.ApiClient;
import ru.ftob.grostore.ucozapi.dto.UcozUserTO;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UcozApiUserRepositoryImpl implements UserRepository {

    private ApiClient client;

    private final static String MODULE_PATH = "/users";

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return new UcozUserTO(client.makeRequest(MODULE_PATH, Verb.GET)).untransform(new User());
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
