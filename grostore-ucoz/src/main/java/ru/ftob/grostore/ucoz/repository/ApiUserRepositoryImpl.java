package ru.ftob.grostore.ucoz.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ftob.grostore.ucoz.ApiClient;
import ru.ftob.grostore.ucoz.to.UcozUser;
import ru.ftob.grostore.ucoz.util.ApiJsonValidator;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ApiUserRepositoryImpl implements ApiBaseRepository<UcozUser> {

    @Autowired
    private ApiClient client;

    private ObjectMapper mapper;

    private final static String MODULE_PATH = "/users";

    public ApiUserRepositoryImpl() {

        mapper = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    @Override
    public UcozUser save(UcozUser ucozUser) throws IOException {
        String jsonInString = mapper.writeValueAsString(ucozUser);
        Map parameters = mapper.readValue(jsonInString, new TypeReference<Map<String, String>>(){});
        Response response = client.makeRequest(MODULE_PATH, Verb.POST, parameters);
        JsonNode savedUserNode = mapper.readTree(response.getBody());

        return mapper.readValue(savedUserNode.toString(), UcozUser.class);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public UcozUser get(String id) throws IOException {

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("user_id", id);
        Response response = client.makeRequest(MODULE_PATH, Verb.GET, parameters);
        JsonNode userNode = mapper.readTree(response.getBody()).findValue("users").get(0);

        return mapper.readValue(userNode.toString(), UcozUser.class);
    }

    @Override
    public List<UcozUser> getAll() throws IOException {

        Response response = client.makeRequest(MODULE_PATH, Verb.GET);
        JsonNode usersNode = mapper.readTree(response.getBody()).findValue("users");

        return mapper.readValue(usersNode.toString(), new TypeReference<List<UcozUser>>(){});
    }
}
