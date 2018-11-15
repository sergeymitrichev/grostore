package ru.ftob.grostore.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"", "/"})
public class DashBoardController {

    @GetMapping
    public ResponseEntity<?> dashboard(Authentication authentication) {
        String name = authentication != null ? authentication.getName() + "(" + authentication.getAuthorities() +  ")"  : "guest";
        return ResponseEntity.ok("Hello from dashboard, " + name + "<hr><form method='POST' action='/logout'><input type='submit' value='Logout'/></form>");
    }
}
