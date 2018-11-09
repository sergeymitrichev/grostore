package ru.ftob.grostore.ucoz.snapshot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface SnapshotWorkerRepository extends CrudRepository<SnapshotWorker, Integer> {

    @Override
    @Transactional
    <S extends SnapshotWorker> S save(S entity);

    @Override
    Optional<SnapshotWorker> findById(Integer integer);

}
