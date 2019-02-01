package ru.ftob.grostore.persistence.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.model.product.Product;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    <S extends Product> S save(S product);

    @Override
    @EntityGraph(value = "Product.detail", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT p FROM Product p WHERE p.id=?1")
    @NonNull
    Optional<Product> findById(@NonNull Integer integer);

    @Override
    Page<Product> findAll(Pageable pageable);

    @Override
    @Transactional
    <S extends Product> List<S> saveAll(Iterable<S> entities);

    @EntityGraph(value = "Product.detail", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT p FROM Product p WHERE p.sku=?1")
    Optional<Product> findBySku(String sku);

    List<Product> findAll();

    List<Product> findAllByUpdatedBy(Account updatedBy);

    List<Product> findAllBySku(List<String> sku);
//    boolean existsByNameOrId()
}
