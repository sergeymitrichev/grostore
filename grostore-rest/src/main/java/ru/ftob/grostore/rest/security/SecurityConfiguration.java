package ru.ftob.grostore.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.service.account.AccountService;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationUserService authenticationUserService;

    @Autowired
    public SecurityConfiguration(AuthenticationUserService authenticationUserService) {
        this.authenticationUserService = authenticationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/").permitAll()
                .anyRequest().hasRole("ADMIN")
                .and()
                .csrf().disable();

        http.logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return this.authenticationUserService;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationUserService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    public PrincipalExtractor principalExtractor(AccountService accountService) {
        return map -> {
            String email = (String) map.get("email");
            Account account = accountService.getByEmail(email).orElseGet(() -> {
                Account newAccount = new Account();
                newAccount.setEmail(email);
                newAccount.setName((String) map.get("name")); // given_name, family_name
                // picture
                // gender
                // locale
                newAccount.setPassword("1q2w3e4R");

                return newAccount;
            });

            account.setVisited(LocalDateTime.now());
            accountService.update(account);

            return account;
        };
    }
}
