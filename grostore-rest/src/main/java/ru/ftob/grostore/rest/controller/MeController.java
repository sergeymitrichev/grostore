package ru.ftob.grostore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.rest.util.ModelMapperUtils;
import ru.ftob.grostore.rest.webmodel.GuiAccount;
import ru.ftob.grostore.service.account.AccountService;
import ru.ftob.grostore.service.util.exception.NotFoundException;

@RestController
@RequestMapping("/me")
@PreAuthorize("hasRole('ROLE_USER')")
public class MeController {

    private final AccountService accountService;

    @Autowired
    public MeController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<?> get() {
        ResponseEntity response = null;
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            Account me = accountService.getByEmail(userDetails.getUsername());
            response = ResponseEntity.ok(ModelMapperUtils.map(me, GuiAccount.class));
        } catch (NotFoundException e) {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return response;

    }
}
