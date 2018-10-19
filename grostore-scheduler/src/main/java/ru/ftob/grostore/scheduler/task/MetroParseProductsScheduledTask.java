package ru.ftob.grostore.scheduler.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ftob.grostore.model.ScheduledTaskConfig;
import ru.ftob.grostore.model.ScheduledTaskConfigStatus;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.scheduler.MetroCatalogParseResult;
import ru.ftob.grostore.service.ScheduledTaskConfigService;
import ru.ftob.grostore.service.product.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class MetroParseProductsScheduledTask implements Runnable {

    private final ScheduledTaskConfigService scheduledTaskConfigService;

    private final ProductService productService;

    private ScheduledTaskConfig config;

    public MetroParseProductsScheduledTask(
            ScheduledTaskConfigService scheduledTaskConfigService,
            ProductService productService,
            ScheduledTaskConfig config
    ) {
        this.scheduledTaskConfigService = scheduledTaskConfigService;
        this.productService = productService;
        this.config = config;
        config.setStatus(ScheduledTaskConfigStatus.SCHEDULED_TASK_WAITING);
        scheduledTaskConfigService.update(config);
    }

    @Override
    public void run() {
        config = scheduledTaskConfigService.get(config.getId());
        config.setStatus(ScheduledTaskConfigStatus.SCHEDULED_TASK_RUNNING);
        scheduledTaskConfigService.update(config);

        ObjectMapper mapper = new ObjectMapper();

        config.getUrl().forEach(url -> {
            //TODO interrupt if cancel
            try {
                MetroCatalogParseResult result = mapper.readValue(new URL(url), MetroCatalogParseResult.class);
                //TODO hide if any product not found in new parse results
                //TODO get all products by config robot name and set enabled to false
                List<Product> products = result.getItems().stream().map(i -> mapper.convertValue(i, Product.class)).collect(Collectors.toList());
                //TODO set products enabled field to true
                //TODO set products createdBy field to generated robot name (with config ID)
                productService.updateAll(products);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        config.setStatus(config.isPeriodic() ? ScheduledTaskConfigStatus.SCHEDULED_TASK_WAITING : ScheduledTaskConfigStatus.SCHEDULED_TASK_DONE);
        scheduledTaskConfigService.update(config);
    }
}
