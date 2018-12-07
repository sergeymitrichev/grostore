package ru.ftob.grostore.scheduler.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ftob.grostore.model.ScheduledTaskConfig;
import ru.ftob.grostore.model.ScheduledTaskConfigStatus;
import ru.ftob.grostore.service.ScheduledTaskConfigService;
import ru.ftob.grostore.service.file.FileStorageService;
import ru.ftob.grostore.service.product.ProductService;
import ru.ftob.grostore.service.productlist.CategoryService;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TaskScanStarter implements Runnable {

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private final ProductService productService;

    private final CategoryService categoryService;

    private final ScheduledTaskConfigService scheduledTaskConfigService;

    private final FileStorageService fileStorageService;

    @Autowired
    public TaskScanStarter(ProductService productService, CategoryService categoryService, ScheduledTaskConfigService scheduledTaskConfigService, FileStorageService fileStorageService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.scheduledTaskConfigService = scheduledTaskConfigService;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public void run() {
        scheduledTaskConfigService.getAll().stream()
                .filter(t -> t.getStatus().equals(ScheduledTaskConfigStatus.SCHEDULED_TASK_NEW))
                .forEach(this::runTask);
    }

    @PostConstruct
    public void startAfterConstruct() {
        executorService.scheduleAtFixedRate(this, 0, 15, TimeUnit.SECONDS);
        //TODO mark all tasks as NEW
    }

    private void runTask(ScheduledTaskConfig config) {
        Runnable task = null;
        switch (config.getType()) {
            case SCHEDULED_TASK_METRO_PARSE_PRODUCTS: {
                task = new MetroParseProductsScheduledTask(
                        scheduledTaskConfigService,
                        productService,
                        categoryService,
                        fileStorageService,
                        config);
                break;
            }
            default: return;
        }
        //TODO add initial delay in config (start date/time)
        if (config.isPeriodic()) {
            executorService.scheduleAtFixedRate(task, 0, config.getDelay(), TimeUnit.SECONDS);
        } else {
            executorService.schedule(task, 0, TimeUnit.SECONDS);
        }
    }
}
