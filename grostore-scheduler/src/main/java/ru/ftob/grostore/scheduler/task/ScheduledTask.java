package ru.ftob.grostore.scheduler.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ftob.grostore.scheduler.MetroCatalogParseResult;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

public class ScheduledTask<V> implements RunnableScheduledFuture<V> {
    @Override
    public boolean isPeriodic() {
        return false;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    @Override
    public void run() {
        System.out.println("Hey from scheduled task");

        String url = "https://nn.metro-cc.ru/category/produkty/ovoschi-griby?limit=20&in_stock=1&virtual_stock=1&format=j";
        ObjectMapper mapper = new ObjectMapper();
        try {
            MetroCatalogParseResult result = mapper.readValue(new URL(url), MetroCatalogParseResult.class);
            System.out.println("Parsed products");
            result.getItems().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
