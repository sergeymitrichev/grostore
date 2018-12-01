package ru.ftob.grostore.ucoz.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ftob.grostore.ucoz.ApiClient;
import ru.ftob.grostore.ucoz.exception.UApiRequestException;
import ru.ftob.grostore.ucoz.to.UcozProduct;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Component
public class ApiProductRepositoryImpl implements ApiBaseRepository<UcozProduct> {

    private final static String MODULE_PATH = "/shop";

    private final ApiClient apiClient;

    private ObjectMapper mapper;

    @Autowired
    public ApiProductRepositoryImpl(ApiClient apiClient) {
        this.apiClient = apiClient;

        mapper = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }


    @Override
    public UcozProduct save(UcozProduct ucozProduct) throws IOException, ExecutionException, InterruptedException, UApiRequestException {
        Response response = apiClient.makeRequest(
                    MODULE_PATH + "/editgoods",
                    Verb.POST,
                    buildParameters(
                            new AbstractMap.SimpleEntry<>("method", "submit"),
                            new AbstractMap.SimpleEntry<>("id", ucozProduct.getId()),
                            new AbstractMap.SimpleEntry<>("cat_id", ucozProduct.getCategory().getId()),
                            new AbstractMap.SimpleEntry<>("name", ucozProduct.getName()),
                            new AbstractMap.SimpleEntry<>("price_in", ucozProduct.getPriceIn().toString()),
                            new AbstractMap.SimpleEntry<>("hide", ucozProduct.getHide().toString())
                    ));
        JsonNode body = mapper.readTree(response.getBody());
        if (null != body && null != body.findValue("success")) {
            return mapper.readValue(mapper.readTree(response.getBody()).findValue("success").findValue("goods_data").toString(), UcozProduct.class);
        }
        throw new UApiRequestException("Cannot update product [" + ucozProduct.getSku() + "]" + ucozProduct.getName() + ". Response: " + response);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public UcozProduct get(String id) throws IOException {
        return null;
    }

    @Override
    public List<UcozProduct> getAll() throws IOException {
        return null;
    }

    public UcozProduct getBySku(String sku) throws ExecutionException, InterruptedException, IOException {
        Response response = apiClient.makeRequest(
                MODULE_PATH + "/request",
                Verb.GET,
                buildParameters(
                        new AbstractMap.SimpleEntry<>("page", "allgoods"),
                        new AbstractMap.SimpleEntry<>("f_art", new String(Base64.getEncoder().encode(sku.getBytes())))
                ));
        try {
            return mapper.readValue(mapper.readTree(response.getBody()).findValue("success").findValue("goods_list").findValue("0").toString(), UcozProduct.class);
        } catch (NullPointerException e) {
            System.out.println("Item not found");
        }
        return null;
    }

    @SafeVarargs
    private final Map<String, String> buildParameters(AbstractMap.SimpleEntry<String, String>... pairs) {
        Map<String, String> param = new HashMap<>();
        for (AbstractMap.SimpleEntry<String, String> pair : pairs) {
            param.put(pair.getKey(), pair.getValue());
        }

        return param;
    }
}
