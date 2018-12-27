package ru.ftob.grostore.rest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.service.account.AccountService;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Account> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @Override
    @NonNull
    public Optional<Account> getCurrentAuditor() {
        Optional<Account> auditor = Optional.empty();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            try {
                UserDetails userDetails =
                        (UserDetails) authentication.getPrincipal();
                auditor = accountService.getByEmail(userDetails.getUsername());
            } catch (ClassCastException e) {
                auditor = Optional.of(accountService.get(1));
                log.error("Can't cast principal to userDetails");
            }
        }
        return auditor;
    }
}
