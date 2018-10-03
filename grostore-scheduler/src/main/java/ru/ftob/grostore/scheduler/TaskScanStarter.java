package ru.ftob.grostore.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ftob.grostore.model.ScheduledTaskConfig;
import ru.ftob.grostore.model.ScheduledTaskConfigStatus;
import ru.ftob.grostore.scheduler.task.MetroParseProductsScheduledTask;
import ru.ftob.grostore.service.ScheduledTaskConfigService;
import ru.ftob.grostore.service.product.ProductService;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

@Component
public class TaskScanStarter implements Runnable {

    private Integer count = 0;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private final ProductService productService;

    private final ScheduledTaskConfigService scheduledTaskConfigService;

    @Autowired
    public TaskScanStarter(ProductService productService, ScheduledTaskConfigService scheduledTaskConfigService) {
        this.productService = productService;
        this.scheduledTaskConfigService = scheduledTaskConfigService;
    }

    @Override
    public void run() {
        // Get all task config from DB
        scheduledTaskConfigService.getAll().stream()
                // Check if config already added to executor
                .filter(t -> t.getStatus().equals(ScheduledTaskConfigStatus.SCHEDULED_TASK_NEW))
                // Convert config to executable future task
                .forEach(this::runTask);

    }

    @PostConstruct
    public void startAfterConstruct() {
        executorService.schedule(this, 1, TimeUnit.SECONDS);
//        executorService.scheduleAtFixedRate(this, 0, 15, TimeUnit.SECONDS);
        //TODO mark all tasks as NEW
    }

    private ScheduledFuture runTask(ScheduledTaskConfig config) {
        Runnable task = null;
        switch (config.getType()) {
            case SCHEDULED_TASK_METRO_PARSE_PRODUCTS: {
                task = new MetroParseProductsScheduledTask(scheduledTaskConfigService, productService, config);
                break;
            }
        }
        //TODO add initial delay in config (start date/time)
        if (config.isPeriodic()) {
            return executorService.scheduleAtFixedRate(task, 0, config.getDelay(), TimeUnit.SECONDS);
        } else {
            return executorService.schedule(task, 0, TimeUnit.SECONDS);
        }
    }
}
