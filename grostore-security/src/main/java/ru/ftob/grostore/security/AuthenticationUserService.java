package ru.ftob.grostore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.service.account.AccountService;

import java.util.Optional;

@Service
public class AuthenticationUserService implements UserDetailsService {

    private final AccountService accountService;

    @Autowired
    public AuthenticationUserService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> account = accountService.getByEmail(email);
        if(account.isPresent()) {
            return new UserDetailsImpl(account.get());
        }
        throw new UsernameNotFoundException(email);
    }
}
