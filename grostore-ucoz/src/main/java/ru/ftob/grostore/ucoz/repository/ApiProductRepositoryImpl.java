package ru.ftob.grostore.ucoz.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ftob.grostore.ucoz.ApiClient;
import ru.ftob.grostore.ucoz.to.UcozProduct;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public UcozProduct save(UcozProduct ucozProduct) throws IOException, ExecutionException, InterruptedException {
        Response response = apiClient.makeRequest(
                    MODULE_PATH + "/editgoods",
                    Verb.POST,
                    buildParameters(
                            new Pair<>("method", "submit"),
                            new Pair<>("id", ucozProduct.getId()),
                            new Pair<>("cat_id", ucozProduct.getCategory().getId()),
                            new Pair<>("name", ucozProduct.getName()),
                            new Pair<>("price_in", ucozProduct.getPriceIn().toString()),
                            new Pair<>("hide", ucozProduct.getHide().toString())
                    ));
        return mapper.readValue(mapper.readTree(response.getBody()).findValue("success").findValue("goods_list").findValue("0").toString(), UcozProduct.class);
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
                        new Pair<>("page", "allgoods"),
                        new Pair<>("f_art", new String(Base64.getEncoder().encode(sku.getBytes())))
                ));
        try {
            return mapper.readValue(mapper.readTree(response.getBody()).findValue("success").findValue("goods_list").findValue("0").toString(), UcozProduct.class);
        } catch (NullPointerException e) {
            System.out.println("Item not found");
        }
        return null;
    }

    @SafeVarargs
    private final Map<String, String> buildParameters(Pair<String, String>... pairs) {
        Map<String, String> param = new HashMap<>();
        for (Pair<String, String> pair : pairs) {
            param.put(pair.getKey(), pair.getValue());
        }
        return param;

    }
}
