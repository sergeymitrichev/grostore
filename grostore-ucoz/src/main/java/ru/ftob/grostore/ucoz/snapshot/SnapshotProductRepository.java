package ru.ftob.grostore.ucoz.snapshot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface SnapshotProductRepository extends CrudRepository<SnapshotProduct, Integer> {

    @Override
    @Transactional
    <S extends SnapshotProduct> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @Transactional
    <S extends SnapshotProduct> S save(S entity);

    @Override
    Optional<SnapshotProduct> findById(Integer integer);

    Optional<SnapshotProduct> findBySku(String sku);
}
