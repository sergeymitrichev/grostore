package ru.ftob.grostore.ucoz.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

    private ObjectMapper mapper;

    private final static String MODULE_PATH = "/users";

    public UserUcozApi() {
        mapper = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    @Override
    public UserUcozTO save(UserUcozTO userUcozTO) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public UserUcozTO get(String id) throws IOException {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("user_id", id);
        Response response = client.makeRequest(MODULE_PATH, Verb.GET, parameters);
        return mapper.readValue(
                mapper.readTree(response.getBody())
                        .findValue("users")
                        .get(0)
                        .toString(),
                UserUcozTO.class);
    }

    @Override
    public List<UserUcozTO> getAll() throws IOException {
        Response response = client.makeRequest(MODULE_PATH, Verb.GET);
        return mapper.readValue(
                mapper.readTree(response.getBody())
                .findValue("users")
                .toString(),
                new TypeReference<List<UserUcozTO>>(){}
        );
    }
}
