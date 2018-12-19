package ru.ftob.grostore.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ru.ftob.grostore.model.account.Account;

@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class PersistenceConfiguration {

    @Bean
    public AuditorAware<Account> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
