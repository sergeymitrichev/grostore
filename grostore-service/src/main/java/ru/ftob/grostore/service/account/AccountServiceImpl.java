package ru.ftob.grostore.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.persistence.account.AccountRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.Collection;

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
    public Collection<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account create(Account account) {
        Assert.notNull(account, "Account must not be null");
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        Assert.notNull(account, "Account must not be null");
        return accountRepository.save(account);
    }

    @Override
    public Collection<Account> updateAll(Collection<Account> t) {
        return accountRepository.saveAll(t);
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteAll(Collection<Account> t) {
        accountRepository.deleteAll(t);
    }

    @Override
    public Account getByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Not found entity with email = " + email));
    }

    @Override
    public Account getByPhone(String phone) {
        return accountRepository.findByPhone(phone).orElseThrow(() -> new NotFoundException("Not found entity with phone = " + phone));
    }
}
