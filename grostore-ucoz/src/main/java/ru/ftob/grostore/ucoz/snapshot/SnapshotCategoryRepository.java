package ru.ftob.grostore.ucoz.snapshot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface SnapshotCategoryRepository extends CrudRepository<SnapshotCategory, Integer> {

    @Override
    @Transactional
    <S extends SnapshotCategory> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Iterable<SnapshotCategory> findAll();

    SnapshotCategory findByName(String name);

    Iterable<SnapshotCategory> findAllByParentId(Integer parentId);
}
