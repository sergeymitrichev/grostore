package ru.ftob.grostore.service.account;

import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.service.BaseService;

public interface AccountService extends BaseService<Account, Integer> {

    Account getByEmail(String email);

    Account getByPhone(String phone);

}
