package ru.ftob.grostore.service.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ftob.grostore.model.account.Account;

public interface AccountService {

    Account get(Integer id);

    Account getByEmail(String email);

    Page<Account> getAll(Pageable pageable);

    void create(Account account);

    void update(Account account);

    void delete(Integer id);

}
