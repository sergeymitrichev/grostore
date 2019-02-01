package ru.ftob.grostore.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.rest.base.ApiError;
import ru.ftob.grostore.rest.util.ModelMapperUtils;
import ru.ftob.grostore.rest.webmodel.GuiAccount;
import ru.ftob.grostore.security.TokenAuthenticationService;
import ru.ftob.grostore.security.UserDetailsImpl;
import ru.ftob.grostore.service.account.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController extends AbstractRestController<Account, Integer, GuiAccount> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AccountService service;

    private final Validator validator;

    private final TokenAuthenticationService tokenAuthenticationService;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public AccountController(
            AccountService service,
            @Qualifier("accountValidator") Validator validator,
            TokenAuthenticationService tokenAuthenticationService, BCryptPasswordEncoder encoder) {
        this.service = service;
        this.validator = validator;
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.encoder = encoder;
        setService(service);
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_ANONYMOUS')")
    public ResponseEntity<?> register(
            @RequestBody GuiAccount guiAccount,
            final BindingResult result,
            final HttpServletRequest request,
            HttpServletResponse res
            ) {
        ResponseEntity<?> response = null;

        // Validate account (email/phone/password)
        validator.validate(guiAccount, result);
        if (result.hasErrors()) {
            log.error("Cant't register account (not valid). \n" + guiAccount);
            //TODO convert errors to readable strings
            ApiError apiError = new ApiError(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Account data not valid",
                    result.getAllErrors()
                            .stream()
                            .map(ObjectError::toString)
                            .collect(Collectors.toList()));
            response = ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(apiError);
        } else {
            Account account = ModelMapperUtils.map(guiAccount, Account.class);
            try {
                account.setPassword(encoder.encode(account.getPassword()));
                Account registered = service.create(account);
                GuiAccount guiRegistered = ModelMapperUtils.map(registered, GuiAccount.class);
                guiRegistered.setPassword(guiAccount.getPassword());
                tokenAuthenticationService.addAuthentication(res, new UserDetailsImpl(account.getEmail(), account.getPassword(), account.getRoles()));
                response = ResponseEntity.ok(guiRegistered);

            } catch (ConstraintViolationException e) {
                //TODO add message converter
                log.error("Can't create account", e);
                ApiError apiError = new ApiError(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Account data not valid",
                        e.getConstraintViolations()
                                .stream()
                                .map(Object::toString)
                                .collect(Collectors.toList()));
                response = ResponseEntity
                        .status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(apiError);
            } catch (Exception e) {
                log.error("Can't create account", e);
                ApiError apiError = new ApiError(
                        HttpStatus.CONFLICT,
                        "Can't create account",
                        e.getMessage()
                );
                response = ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(apiError);
            }

        }

        return response;
    }
}
