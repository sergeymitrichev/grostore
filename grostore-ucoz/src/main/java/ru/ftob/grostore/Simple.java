package ru.ftob.grostore;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.ftob.grostore.ucoz.ApiClient;
import ru.ftob.grostore.ucoz.repository.ApiProductRepositoryImpl;
import ru.ftob.grostore.ucoz.to.UcozProduct;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

class Simple {
    private int value;

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext();
        appCtx.load("spring-app.xml");
        appCtx.refresh();
//
        ApiClient client = appCtx.getBean(ApiClient.class);
        ApiProductRepositoryImpl productRepository = appCtx.getBean(ApiProductRepositoryImpl.class);
        UcozProduct ucozProduct = productRepository.getBySku("73170");
        System.out.println(ucozProduct);
        System.out.println(productRepository.save(ucozProduct));
//        ApiUserRepositoryImpl userRepository = appCtx.getBean(ApiUserRepositoryImpl.class);
//        UcozUser account = userRepository.get("1");
//        System.out.println(account);
//
////        List<UcozUser> users = userRepository.getAll();
////        users.forEach(System.out::println);
//
//        account = new UcozUser("test123", "tester", "smmit30@mail.ru", "1q2w3e4r");
//        UcozUser savedUser = userRepository.save(account);
//        System.out.println(savedUser);
//
////          {"error":{"msg":"Not supported method","code":"NOT_SUPPORTED_METHOD"}} <- NPE
//        ApiOrderRepositoryImpl orderUcozApi = appCtx.getBean(ApiOrderRepositoryImpl.class);
////        UcozOrder order = orderUcozApi.get("UqSsczak%3Bxy7hLyQcMNjBvKlJZYhYKng6ihi6Kqvy%3Boo-auth-366c955a4ac8ce442a6afdf7279fa128");
//        UcozOrder order = orderUcozApi.get("UqSsczak%3Bxy7hLyQcMNjBvKlJZYhYKng6ihi6Kqvy%3Boo");
//        System.out.println(order);
//        System.out.println("*** Orders ***");
//
//        List<UcozOrder> orders = orderUcozApi.getAll();
//        System.out.println(orders);
    }
}
