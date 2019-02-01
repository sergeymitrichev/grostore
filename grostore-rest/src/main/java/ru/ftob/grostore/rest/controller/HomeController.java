package ru.ftob.grostore.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.ftob.grostore.rest.config.RestUrlMappingConstants.HOME_MAPPING;

@RestController
@RequestMapping(HOME_MAPPING)
public class HomeController {

    @GetMapping
    public ResponseEntity<?> dashboard() {
        return ResponseEntity.ok().build();
    }
}
