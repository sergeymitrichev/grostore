package ru.ftob.grostore.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @RequestMapping("/import")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    // TODO upload files http://spring.io/guides/gs/uploading-files/
}
