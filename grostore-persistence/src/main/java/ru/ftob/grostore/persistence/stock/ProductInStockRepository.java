package ru.ftob.grostore.persistence.stock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.stock.ProductInStock;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ProductInStockRepository extends PagingAndSortingRepository<ProductInStock, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ProductInStock s WHERE s.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    @NonNull
    <S extends ProductInStock> S save(S product);

    @Override
    @NonNull
    Optional<ProductInStock> findById(Integer integer);

    @Override
    @NonNull
    Page<ProductInStock> findAll(Pageable pageable);

}
