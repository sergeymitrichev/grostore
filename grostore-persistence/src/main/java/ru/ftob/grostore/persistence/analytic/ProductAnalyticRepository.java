package ru.ftob.grostore.persistence.analytic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.analytics.ProductAnalyticByOrigin;
import ru.ftob.grostore.model.product.Product;

import java.util.Collection;
import java.util.List;

public interface ProductAnalyticRepository extends PagingAndSortingRepository<ProductAnalyticByOrigin, Integer> {

    Page<ProductAnalyticByOrigin> findAllByProduct(Product product, Pageable pageable);

    @Transactional
    @Modifying
    @Query("DELETE FROM Stock s WHERE s.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    @NonNull
    <S extends ProductAnalyticByOrigin> Collection<S> saveAll(@NonNull Iterable<S> entities);

    @Override
    @NonNull
    List<ProductAnalyticByOrigin> findAll();
}
