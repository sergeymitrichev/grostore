package ru.ftob.grostore;

import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.ftob.grostore.ucoz.UcozApiClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

class Simple {
    private int value;

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) throws IOException {
        GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext();
        appCtx.load("spring-app.xml");
        appCtx.refresh();

        UcozApiClient client = appCtx.getBean(UcozApiClient.class);
        Response response = client.makeRequest("/users", Verb.GET, Collections.emptyMap());
        System.out.println(response.getBody());
    }
}
