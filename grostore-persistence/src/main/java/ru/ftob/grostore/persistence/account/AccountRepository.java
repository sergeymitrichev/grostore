package ru.ftob.grostore.persistence.account;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.account.Account;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {

    Optional<Account> findByEmail(String email);

    Optional<Account> findByPhone(String phone);

    Optional<Account> findByEmailOrPhone(String email, String phone);

    @Override
    @NonNull
    List<Account> findAll();

    @Override
    @Transactional
    @NonNull
    <S extends Account> Collection<S> saveAll(@NonNull Iterable<S> entities);

}
