package ru.ftob.grostore.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.ftob.grostore.rest.storage.StorageProperties;
import ru.ftob.grostore.rest.storage.StorageService;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan({"ru.ftob.grostore.service", "ru.ftob.grostore.persistence", "ru.ftob.grostore.rest", "ru.ftob.grostore.ucoz"})
@EnableJpaRepositories({"ru.ftob.grostore.persistence", "ru.ftob.grostore.ucoz.snapshot"})
@EntityScan({"ru.ftob.grostore.model", "ru.ftob.grostore.ucoz.snapshot"})
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
//            storageService.deleteAll();
            storageService.init();
        };
    }

//    @Bean
//    ApiClient apiClient(
//            @Value("${ucoz.apiKey}") String apiKey,
//            @Value("${ucoz.apiSecret}") String apiSecret,
//            @Value("${ucoz.token}") String token,
//            @Value("${ucoz.tokenSecret}") String tokenSecret,
//            @Value("${ucoz.siteUrl}") String siteUrl
//    ){
//        return new ApiClient(
//                apiKey,
//                apiSecret,
//                token,
//                tokenSecret,
//                siteUrl
//        );
//    }

    Map<String,?> additionalJpaProperties(){
        Map<String,String> map = new HashMap<>();

        map.put("hibernate.hbm2ddl.auto", "create");
        map.put("hibernate.show_sql", "true");

        return map;
    }
}
