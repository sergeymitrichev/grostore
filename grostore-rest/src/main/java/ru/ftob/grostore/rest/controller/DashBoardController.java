package ru.ftob.grostore.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"", "/"})
public class DashBoardController {

    @GetMapping
    public ResponseEntity<?> dashboard() {

        return ResponseEntity.ok("Hello from dashboard, " + "");
    }
}
