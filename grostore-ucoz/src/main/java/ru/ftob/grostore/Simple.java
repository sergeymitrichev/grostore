package ru.ftob.grostore;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.ftob.grostore.ucoz.ApiClient;
import ru.ftob.grostore.ucoz.repository.ApiUserRepositoryImpl;
import ru.ftob.grostore.ucoz.to.UcozUser;

import java.io.IOException;
import java.util.List;

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

        ApiClient client = appCtx.getBean(ApiClient.class);
        ApiUserRepositoryImpl userRepository = appCtx.getBean(ApiUserRepositoryImpl.class);
        UcozUser user = userRepository.get("1");
        System.out.println(user);

        List<UcozUser> users = userRepository.getAll();
        users.forEach(System.out::println);

//          {"error":{"msg":"Not supported method","code":"NOT_SUPPORTED_METHOD"}} <- NPE
//        ApiOrderRepositoryImpl orderUcozApi = appCtx.getBean(ApiOrderRepositoryImpl.class);
//        UcozOrder order = orderUcozApi.get("UqSsczak%3Bxyeq%5E%21DcMNjBvbsJZUqYK396iqO4vK4y%3Boo");
//        System.out.println(order);
    }
}
