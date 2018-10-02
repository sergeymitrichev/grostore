package ru.ftob.grostore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ftob.grostore.model.ScheduledTaskConfig;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.io.IOException;

public interface ScheduledTaskConfigService {

    ScheduledTaskConfig create(ScheduledTaskConfig config);

    void delete(Integer id) throws NotFoundException;

    ScheduledTaskConfig get(Integer id) throws NotFoundException, IOException;

    ScheduledTaskConfig update(ScheduledTaskConfig config);

    Page<ScheduledTaskConfig> getAll(Pageable pageable);

}
