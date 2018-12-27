package ru.ftob.grostore.rest.stock;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ftob.grostore.model.stock.Stock;
import ru.ftob.grostore.persistence.stock.StockRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase
public class StockRepositoryIntegrationTest {

    @Autowired
    private StockRepository stockRepository;

    private final Stock STOCK = new Stock();
    private final String STOCK_NAME = "New Stock";

    @Before
    public void init() {
        STOCK.setName(STOCK_NAME);
        Stock saved = stockRepository.save(STOCK);
        STOCK.setId(saved.getId());
    }

    @Test
    public void whenFindById_thenReturnStock() {
        assert STOCK.getId() != null;
        Stock found = stockRepository.findById(STOCK.getId()).orElse(null);
        Assertions.assertThat(found.getName()).isEqualTo(STOCK_NAME);
    }

    @Test
    public void whenFindAll_thenReturnStocks() {
        Stock stock = new Stock();
        stock.setName("Another stock");
        stock = stockRepository.save(stock);
        List<Stock> found = stockRepository.findAll();

        Assertions.assertThat(found).containsExactly(STOCK, stock);
    }

    @Test
    public void whenFindByName_thenReturnStock() {
        Stock found = stockRepository.findByName(STOCK.getName()).orElse(null);
        assert found != null;
        Assertions.assertThat(found.getName()).isEqualTo(STOCK.getName());
    }

    @Test
    public void whenSave_thenReturnStock() {

        Stock saved = stockRepository.save(STOCK);
        Assertions.assertThat(saved).isEqualTo(STOCK);
    }
}
