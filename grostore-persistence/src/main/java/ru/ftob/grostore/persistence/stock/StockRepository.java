package ru.ftob.grostore.persistence.stock;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.stock.Stock;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface StockRepository extends PagingAndSortingRepository<Stock, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Stock s WHERE s.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    @NonNull
    <S extends Stock> S save(@NonNull S stock);

    @Override
    @NonNull
    Optional<Stock> findById(@NonNull Integer integer);

    Optional<Stock> findByName(String name);

    @Override
    @NonNull
    List<Stock> findAll();

    @Override
    @Transactional
    @NonNull
    <S extends Stock> Collection<S> saveAll(@NonNull Iterable<S> entities);

    @EntityGraph(attributePaths = {"products"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT s FROM Stock s WHERE s.id=?1")
    <S extends Stock> Stock getWithProducts(Integer id);
}
