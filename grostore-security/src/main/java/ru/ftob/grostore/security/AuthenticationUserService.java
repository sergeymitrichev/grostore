package ru.ftob.grostore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.persistence.account.AccountRepository;

import java.util.Optional;

@Service
public class AuthenticationUserService implements UserDetailsService {

    private final AccountRepository repository;

    @Autowired
    public AuthenticationUserService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> account = repository.findByEmail(email);
        if(account.isPresent()) {
            return new UserDetailsImpl(account.get());
        }
        throw new UsernameNotFoundException(email);
    }
}
