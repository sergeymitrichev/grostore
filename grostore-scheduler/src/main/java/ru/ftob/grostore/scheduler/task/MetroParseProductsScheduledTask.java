package ru.ftob.grostore.scheduler.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ftob.grostore.model.ScheduledTaskConfig;
import ru.ftob.grostore.model.ScheduledTaskConfigStatus;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.scheduler.MetroCatalogParseResult;
import ru.ftob.grostore.service.ScheduledTaskConfigService;
import ru.ftob.grostore.service.product.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class MetroParseProductsScheduledTask implements RunnableScheduledFuture<MetroCatalogParseResult> {

    private final ScheduledTaskConfigService scheduledTaskConfigService;

    private final ProductService productService;

    private ScheduledTaskConfig config;

    private MetroCatalogParseResult result;

    public MetroParseProductsScheduledTask(
            ScheduledTaskConfigService scheduledTaskConfigService,
            ProductService productService,
            ScheduledTaskConfig config
    ) {
        this.scheduledTaskConfigService = scheduledTaskConfigService;
        this.productService = productService;
        this.config = config;
        this.config.setStatus(ScheduledTaskConfigStatus.SCHEDULED_TASK_WAITING);
        scheduledTaskConfigService.update(config);
    }

    @Override
    public boolean isPeriodic() {
        return config.isPeriodic();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return config.getDelay();
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    @Override
    public void run() {

        config.setStatus(ScheduledTaskConfigStatus.SCHEDULED_TASK_RUNNING);
        scheduledTaskConfigService.update(config);

        ObjectMapper mapper = new ObjectMapper();

        config.getUrl().forEach(url -> {
            //TODO interrupt if cancel
            try {
                MetroCatalogParseResult result = mapper.readValue(new URL(url), MetroCatalogParseResult.class);
                List<Product> products = result.getItems().stream().map(i -> mapper.convertValue(i, Product.class)).collect(Collectors.toList());
                productService.updateAll(products);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        config.setStatus(config.isPeriodic() ? ScheduledTaskConfigStatus.SCHEDULED_TASK_WAITING : ScheduledTaskConfigStatus.SCHEDULED_TASK_DONE);
        scheduledTaskConfigService.update(config);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        //TODO implement
        return false;
    }

    @Override
    public boolean isCancelled() {
        return config.getStatus().equals(ScheduledTaskConfigStatus.SCHEDULED_TASK_CANCELLED);
    }

    @Override
    public boolean isDone() {
        return config.getStatus().equals(ScheduledTaskConfigStatus.SCHEDULED_TASK_DONE);
    }

    @Override
    public MetroCatalogParseResult get() throws InterruptedException, ExecutionException {
        //TODO implement
        return result;
    }

    @Override
    public MetroCatalogParseResult get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        //TODO implement
        return result;
    }
}
