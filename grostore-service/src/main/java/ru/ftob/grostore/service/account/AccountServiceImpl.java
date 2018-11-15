package ru.ftob.grostore.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.persistence.account.AccountRepository;

import java.util.Optional;

import static ru.ftob.grostore.service.util.ValidationUtil.checkNotFoundWithId;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account get(Integer id) {
        Account account = accountRepository.findById(id).orElse(null);
        return checkNotFoundWithId(account, id);
    }

    @Override
    public Page<Account> getAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public void create(Account account) {
        Assert.notNull(account, "Account must not be null");
        accountRepository.save(account);
    }

    @Override
    public void update(Account account) {
        Assert.notNull(account, "Account must not be null");
        accountRepository.save(account);
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> getByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
