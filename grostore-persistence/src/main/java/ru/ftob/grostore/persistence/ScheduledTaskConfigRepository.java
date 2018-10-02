package ru.ftob.grostore.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.ScheduledTaskConfig;

@Transactional(readOnly = true)
public interface ScheduledTaskConfigRepository extends PagingAndSortingRepository<ScheduledTaskConfig, Integer> {

}
