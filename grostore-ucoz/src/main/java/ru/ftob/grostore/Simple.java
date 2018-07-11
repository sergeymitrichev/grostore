package ru.ftob.grostore;

import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.ftob.grostore.ucoz.UcozApiClient;

import java.util.Arrays;

class Simple {
    private int value;

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext();
        appCtx.load("spring-app.xml");
        appCtx.refresh();

        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        UcozApiClient client = appCtx.getBean(UcozApiClient.class);
        Response response = client.makeRequest("/users", Verb.GET, null);
        System.out.println(response);
    }
}
