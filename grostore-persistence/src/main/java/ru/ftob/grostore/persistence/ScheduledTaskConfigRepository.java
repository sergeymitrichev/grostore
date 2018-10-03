package ru.ftob.grostore.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.ScheduledTaskConfig;

import java.util.List;

@Transactional(readOnly = true)
public interface ScheduledTaskConfigRepository extends PagingAndSortingRepository<ScheduledTaskConfig, Integer> {

    @Override
    @Transactional
    <S extends ScheduledTaskConfig> S save(S entity);

    @Override
    List<ScheduledTaskConfig> findAll();

}
