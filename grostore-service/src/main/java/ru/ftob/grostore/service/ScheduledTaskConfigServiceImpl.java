package ru.ftob.grostore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.scheduled.ScheduledTaskConfig;
import ru.ftob.grostore.persistence.ScheduledTaskConfigRepository;
import ru.ftob.grostore.service.account.AccountService;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.ftob.grostore.service.util.ValidationUtil.checkExist;
import static ru.ftob.grostore.service.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ScheduledTaskConfigServiceImpl implements ScheduledTaskConfigService {

    private final ScheduledTaskConfigRepository repository;

    private final AccountService accountService;

    private static final Integer ROBOT_ACCOUNT_ID = 2;

    @Autowired
    public ScheduledTaskConfigServiceImpl(ScheduledTaskConfigRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    @Override
    public ScheduledTaskConfig create(ScheduledTaskConfig config) {
        Assert.notNull(config, "Scheduled task config must not be null");
        config.setCreated(LocalDateTime.now());
        //TODO replace to real user account
        config.setCreatedBy(accountService.get(ROBOT_ACCOUNT_ID));
        return repository.save(config);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        repository.deleteById(id);
    }

    @Override
    public ScheduledTaskConfig get(Integer id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public ScheduledTaskConfig update(ScheduledTaskConfig config) {
        Assert.notNull(config, "Scheduled task config must not be null");
        checkExist(config);
        return checkNotFoundWithId(repository.save(config), config.getId());
    }

    @Override
    public Page<ScheduledTaskConfig> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ScheduledTaskConfig> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteAll(List<ScheduledTaskConfig> configs) {
        repository.deleteAll(configs);
    }
}
