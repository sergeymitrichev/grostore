package ru.ftob.grostore.ucoz.api;

import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ftob.grostore.ucoz.UcozApiClient;
import ru.ftob.grostore.ucoz.to.UserUcozTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class UserUcozApi implements UcozApi<UserUcozTO> {

    @Autowired
    private UcozApiClient client;

    private final static String MODULE_PATH = "/users";

    @Override
    public UserUcozTO save(UserUcozTO userUcozTO) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public UserUcozTO get(String id) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("user_id", id);
        Response response = client.makeRequest(MODULE_PATH, Verb.GET, parameters);
        try {
            response.getBody();

        } catch (IOException e) {
            e.printStackTrace(); //TODO add logger
        }
        return null;
    }

    @Override
    public List<UserUcozTO> getAll() {
        return null;
    }
}
