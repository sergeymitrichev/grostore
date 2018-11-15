package ru.ftob.grostore.ucoz.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ftob.grostore.ucoz.ApiClient;
import ru.ftob.grostore.ucoz.to.UcozUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/*TODO
uAPI(request)/Client -> JSON(objectMapper)/Transformer -> DTO(modelMapper)/Repository -> Model/Service
No login in model for creating new uAPI object (account). How to be?
 */
@Component
public class ApiUserRepositoryImpl implements ApiBaseRepository<UcozUser> {

    private final ApiClient client;

    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

    private ModelMapper modelMapper = new ModelMapper();

    private final static String MODULE_PATH = "/users";

    @Autowired
    public ApiUserRepositoryImpl(ApiClient client) {

        this.client = client;
    }

    @Override
    public UcozUser save(UcozUser user) throws IOException {
        Verb method = Verb.PUT;
        if (user.getId() != null) {
            method = Verb.POST;
        }
        String jsonInString = null;
        try {
            jsonInString = objectMapper.writeValueAsString(modelMapper.map(user, UcozUser.class));
            Map parameters = objectMapper.readValue(jsonInString, new TypeReference<Map<String, String>>() {
            });
            Response response = null;
            try {
                response = client.makeRequest(MODULE_PATH, method, parameters);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return objectMapper.readValue(objectMapper.readTree(response.getBody()).toString(), UcozUser.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {

        //TODO delete account by email in uAPI
        String email = "";
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("email", email);
        Response response = null;
        try {
            response = client.makeRequest(MODULE_PATH, Verb.DELETE, parameters);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            return null != objectMapper.readTree(response.getBody()).findValue("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UcozUser get(String id) throws IOException {
        //TODO get account by email in uAPI
        String email = "";
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("email", email);
        Response response = null;
        try {
            response = client.makeRequest(MODULE_PATH, Verb.GET, parameters);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JsonNode userNode = null;
        try {
            userNode = objectMapper.readTree(response.getBody()).findValue("users").get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UcozUser ucozUser = null;
        try {
            ucozUser = objectMapper.readValue(userNode.toString(), UcozUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ucozUser;
    }

    @Override
    public List<UcozUser> getAll() {

        Response response = null;
        try {
            response = client.makeRequest(MODULE_PATH, Verb.GET);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JsonNode usersNode = null;
        try {
            usersNode = objectMapper.readTree(response.getBody()).findValue("users");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<UcozUser> uCozUsers = new ArrayList<>();
        try {
            uCozUsers = objectMapper.readValue(usersNode.toString(), new TypeReference<List<UcozUser>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uCozUsers;
    }

    public int count() throws IOException {

        Response response = null;
        try {
            response = client.makeRequest(MODULE_PATH, Verb.GET);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JsonNode totalUsers = objectMapper.readTree(response.getBody()).findValue("total_users");

        return objectMapper.readValue(totalUsers.toString(), Integer.class);
    }
}
