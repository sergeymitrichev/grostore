package ru.ftob.grostore.ucoz.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ftob.grostore.ucoz.ApiClient;
import ru.ftob.grostore.ucoz.to.UcozOrder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class ApiOrderRepositoryImpl implements ApiBaseRepository<UcozOrder> {

    @Autowired
    private ApiClient client;

    private ObjectMapper mapper;

    private final static String MODULE_PATH = "/shop";

    public ApiOrderRepositoryImpl() {
        mapper = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    @Override
    public UcozOrder save(UcozOrder ucozOrder) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    // id ~ hash
    public UcozOrder get(String id) throws IOException {
        //https://minutka-nn.ru/shop/order/UqSsczak%3Bxyeq%5E%21DcMNjBvbsJZUqYK396iqO4vK4y%3Boo
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("order", id);

        Response response = null;
        try {
            response = client.makeRequest(MODULE_PATH + "/order/", Verb.GET, parameters);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println(response.getBody());
        return mapper.readValue(
                mapper.readTree(response.getBody())
                        .findValue("success")
                        .toString(),
                UcozOrder.class);
    }

    @Override
    public List<UcozOrder> getAll() throws IOException {
        Response response = null;
        try {
            response = client.makeRequest(MODULE_PATH + "/invoices/", Verb.GET);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println(response.getBody());
        return mapper.readValue(
                mapper.readTree(response.getBody())
                        .findValue("success")
                        .toString(),
                new TypeReference<List<UcozOrder>>(){});
    }
}
