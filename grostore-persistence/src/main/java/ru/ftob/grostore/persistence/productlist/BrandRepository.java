package ru.ftob.grostore.persistence.productlist;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.productlist.Brand;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface BrandRepository extends PagingAndSortingRepository<Brand, Integer> {

    @NonNull
    List<Brand> findAll();

    @Override
    @Transactional
    @NonNull
    <S extends Brand> List<S> saveAll(Iterable<S> entities);

    Optional<Brand> findByName(String name);
}
