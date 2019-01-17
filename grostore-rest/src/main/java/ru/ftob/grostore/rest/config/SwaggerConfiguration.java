package ru.ftob.grostore.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    private ApplicationContext context;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.ftob.grostore"))
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths((PathSelectors.any()))
                .build();
    }
    private ApiInfo apiInfo() {
        BuildProperties buildProperties = context.getBean(BuildProperties.class);
        return new ApiInfo(
                "My REST API",
                "Some custom description of API.",
                buildProperties.getVersion(),
                "Terms of service",
                new Contact("John Doe", "http://www.example.com", "myeaddress@company.com"),
                "License of API", "http://www.example.com", Collections.emptyList());
    }
}
