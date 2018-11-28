package ru.ftob.grostore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.rest.util.ModelMapperUtils;
import ru.ftob.grostore.rest.webmodel.GuiAccount;
import ru.ftob.grostore.service.account.AccountService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController /*extends AbstractRestController<Account, Integer, GuiAccount> */{

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(Pageable pageable) {
        Page<Account> accountPage = accountService.getAll(pageable);
        List<GuiAccount> accounts =accountPage.getContent().stream().map(
                p -> ModelMapperUtils.map(p, GuiAccount.class)
        ).collect(Collectors.toList());
        PageImpl page = new PageImpl<>(accounts, pageable, accountPage.getTotalElements());

        return ResponseEntity.ok(page);
    }
}
