package ru.ftob.grostore.scheduler.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ftob.grostore.model.ScheduledTaskConfig;
import ru.ftob.grostore.model.ScheduledTaskConfigStatus;
import ru.ftob.grostore.model.product.Product;
import ru.ftob.grostore.model.productlist.Category;
import ru.ftob.grostore.scheduler.task.to.MetroPage;
import ru.ftob.grostore.scheduler.task.deserializer.MetroPageDeserializer;
import ru.ftob.grostore.service.ScheduledTaskConfigService;
import ru.ftob.grostore.service.file.FileStorageService;
import ru.ftob.grostore.service.product.ProductService;
import ru.ftob.grostore.service.productlist.CategoryService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.ftob.grostore.scheduler.config.SchedulerConstants.NEW_PRODUCTS_DEFAULT_CATEGORY_ID;
import static ru.ftob.grostore.scheduler.config.SchedulerConstants.PRODUCTS_IMAGE_PATH;

public class MetroParseProductsScheduledTask implements Runnable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ScheduledTaskConfigService scheduledTaskConfigService;

    private final ProductService productService;

    private final CategoryService categoryService;

    private final FileStorageService fileStorageService;

    private ScheduledTaskConfig config;

    public MetroParseProductsScheduledTask(
            ScheduledTaskConfigService scheduledTaskConfigService,
            ProductService productService,
            CategoryService categoryService,
            FileStorageService fileStorageService,
            ScheduledTaskConfig config
    ) {
        this.scheduledTaskConfigService = scheduledTaskConfigService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.fileStorageService = fileStorageService;
        this.config = config;
        setTaskStatus(ScheduledTaskConfigStatus.SCHEDULED_TASK_WAITING);
    }

    @Override
    public void run() {
        config = scheduledTaskConfigService.get(config.getId());
        setTaskStatus(ScheduledTaskConfigStatus.SCHEDULED_TASK_RUNNING);

        Set<Product> parsedProducts = fetchProducts();
        Set<Product> persistedProducts = new HashSet<>(productService
                .getAllByUpdatedBy(config.getProductsCreator()));
        Set<Product> productsToCreate = new HashSet<>();
        Set<Product> productsToUpdate = new HashSet<>();

        Category defaultCategory = categoryService.get(NEW_PRODUCTS_DEFAULT_CATEGORY_ID);

        parsedProducts
                .forEach(p -> {
                    Product persisted = productService.getBySku(p.getSku());
                    if (persisted == null) {
                        p.setEnabled(false);
                        p.addCategory(defaultCategory);
                        try {
                            p.setImage(fileStorageService.store(p.getImage(), PRODUCTS_IMAGE_PATH + p.getSku()));
                        } catch (IOException e) {
                            log.error("Cannot save product image", e);
                        }
                        productsToCreate.add(p);
                    } else {
                        //TODO recalc out prices
                        persisted.setPrices(p.getPrices());
                        productsToUpdate.add(persisted);
                    }
                });

        Set<Product> productsToDisable = persistedProducts
                .stream()
                .filter(p -> !productsToUpdate.contains(p))
                .peek(persisted -> {
                    persisted.setEnabled(false);
                })
                .collect(Collectors.toSet());

        List<Product> products = new ArrayList<>();
        log.debug("Created products: " + productsToCreate);
        products.addAll(productsToCreate);
        log.debug("Updated products: " + productsToCreate);
        products.addAll(productsToUpdate);
        log.debug("Disabled products: " + productsToCreate);
        products.addAll(productsToDisable);
        try {
            productService.updateAll(products);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        setTaskStatus(
                config.isPeriodic() ?
                        ScheduledTaskConfigStatus.SCHEDULED_TASK_WAITING :
                        ScheduledTaskConfigStatus.SCHEDULED_TASK_DONE);
    }

    private void setTaskStatus(ScheduledTaskConfigStatus status) {
        this.config.setStatus(status);
        this.scheduledTaskConfigService.update(this.config);
    }

    private Set<Product> fetchProducts() {
        Set<Product> fetched = new HashSet<>();
        config.getUrl().forEach(url -> {

            if (ScheduledTaskConfigStatus.SCHEDULED_TASK_CANCELLED.equals(config.getStatus())) {
                return;
            }

            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(
                    MetroPage.class,
                    new MetroPageDeserializer());
            mapper.registerModule(module);

            try {
                MetroPage page = mapper.readValue(new URL(url.getLink()), MetroPage.class);
                page.getProducts().forEach(p -> {
                    p.addCategory(url.getCategory());
                    p.setCreatedBy(config.getProductsCreator());
                    p.setUpdatedBy(config.getProductsCreator());
                });
                log.debug("URL parsed: " + url.getLink() + ". Products parsed: " + page.getProducts().size());
                fetched.addAll(page.getProducts());
            } catch (IOException e) {
                log.error("Cannot parse URL " + url.getLink(), e);
            }
        });
        return fetched;
    }
}