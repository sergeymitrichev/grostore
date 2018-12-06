package ru.ftob.grostore.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.ftob.grostore.service.ScheduledTaskConfigService;
import ru.ftob.grostore.service.file.FileStorageService;
import ru.ftob.grostore.service.product.ProductService;
import ru.ftob.grostore.service.productlist.CategoryService;

@SpringBootApplication
@ComponentScan({"ru.ftob.grostore.service", "ru.ftob.grostore.persistence", "ru.ftob.grostore.scheduler.*"})
@EnableJpaRepositories({"ru.ftob.grostore.persistence"})
@EntityScan({"ru.ftob.grostore.model"})
@PropertySource({
        "file:/var/lib/grostore/conf/application.yml",
        "file:/var/lib/grostore/conf/db_connection.properties"})
public class SchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class);
    }

    @Bean("taskScanStarter")
    TaskScanStarter taskScanStarter(
            ProductService productService,
            CategoryService categoryService,
            ScheduledTaskConfigService scheduledTaskConfigService,
            FileStorageService fileStorageService
    ) {
        return new TaskScanStarter(productService, categoryService, scheduledTaskConfigService, fileStorageService);
    }
}
