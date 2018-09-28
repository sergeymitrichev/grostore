package ru.ftob.grostore.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ftob.grostore.scheduler.task.ScheduledTask;
import ru.ftob.grostore.service.product.ProductService;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TaskScanStarter implements Runnable {

    private Integer count = 0;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private final ProductService productService;

    @Autowired
    public TaskScanStarter(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run() {

        executorService.schedule(new ScheduledTask<>(), 1, TimeUnit.SECONDS);


        // Get all task config from DB
        // Check if config already added to executor
        // Convert config to executable future task
        // Add task to executor
        // Ping this (task scan starter) after adding new task config
    }

    @PostConstruct
    public void startAfterConstruct() {
        executorService.schedule(this, 1, TimeUnit.SECONDS);
//        executorService.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS);
    }
}
