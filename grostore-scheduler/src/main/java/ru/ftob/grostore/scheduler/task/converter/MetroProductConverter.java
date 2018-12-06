package ru.ftob.grostore.scheduler.task.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ftob.grostore.model.product.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

public class MetroProductConverter implements SchedulerConverter<URL, Product> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Collection<Product> convert(URL source) {
        try {
            MetroPage catalog = mapper.readValue(source, MetroPage.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
