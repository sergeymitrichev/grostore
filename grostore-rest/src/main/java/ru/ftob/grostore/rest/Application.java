package ru.ftob.grostore.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.ftob.grostore.rest.storage.StorageProperties;
import ru.ftob.grostore.rest.storage.StorageService;
import ru.ftob.grostore.ucoz.ApiClient;

import static ru.ftob.grostore.rest.config.RestConstants.REST_APPLICATION_PROPERTIES_FILE;
import static ru.ftob.grostore.rest.config.RestConstants.REST_DB_CONNECTION_PROPERTIES_FILE;
import static ru.ftob.grostore.rest.config.RestConstants.UCOZ_API_KEY;

@SpringBootApplication
@ComponentScan({"ru.ftob.grostore.service", "ru.ftob.grostore.persistence", "ru.ftob.grostore.rest", "ru.ftob.grostore.security", "ru.ftob.grostore.ucoz"})
@EnableJpaRepositories({"ru.ftob.grostore.persistence", "ru.ftob.grostore.ucoz.snapshot"})
@EntityScan({"ru.ftob.grostore.model", "ru.ftob.grostore.security", "ru.ftob.grostore.ucoz.snapshot"})
@EnableConfigurationProperties({StorageProperties.class})
@PropertySource({REST_APPLICATION_PROPERTIES_FILE, REST_DB_CONNECTION_PROPERTIES_FILE})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }

    @Bean
    @ConfigurationProperties(UCOZ_API_KEY)
    ApiClient apiClient(
            @Value("${apiKey}") String apiKey,
            @Value("${apiSecret}") String apiSecret,
            @Value("${token}") String token,
            @Value("${tokenSecret}") String tokenSecret,
            @Value("${siteUrl}") String siteUrl
    ){
        return new ApiClient(
                apiKey,
                apiSecret,
                token,
                tokenSecret,
                siteUrl
        );
    }
}
