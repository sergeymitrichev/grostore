package ru.ftob.grostore.persistence.account;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.account.Account;

import java.util.Optional;

@Transactional(readOnly = true)
public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {

//    @Override
//    <S extends Account> S save(S account);
//
//    @Override
//    <S extends Account> Iterable<S> saveAll(Iterable<S> accounts);
//
    @Override
    Optional<Account> findById(Integer id);
//
//    @Override
//    Iterable<Account> findAll();
//
//    @Override
//    long count();
//
//    @Override
//    void deleteById(Integer id);

}
