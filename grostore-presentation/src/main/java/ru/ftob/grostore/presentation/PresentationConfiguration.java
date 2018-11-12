package ru.ftob.grostore.presentation;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PresentationConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/greeting").setViewName("greeting");
        registry.addViewController("/").setViewName("greeting");
        registry.addViewController("/secured").setViewName("secured");
        registry.addViewController("/login").setViewName("login");
    }
}
