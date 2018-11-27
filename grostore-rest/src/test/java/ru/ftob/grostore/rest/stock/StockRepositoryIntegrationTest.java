package ru.ftob.grostore.rest.stock;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ftob.grostore.model.stock.Stock;
import ru.ftob.grostore.persistence.stock.StockRepository;

import java.util.List;

@TestPropertySource(locations="classpath:application.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class StockRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StockRepository stockRepository;

    private final Stock STOCK = new Stock();
    private final String STOCK_NAME = "New Stock";

    @Before
    public void init() {
        STOCK.setName(STOCK_NAME);
    }

    @Test
    public void whenFindById_thenReturnStock() {
        Integer id = (Integer) entityManager.persistAndGetId(STOCK);
        Stock found = stockRepository.findById(id).get();
        Assertions.assertThat(found.getName()).isEqualTo(STOCK_NAME);
    }

    @Test
    public void whenFindAll_thenReturnStocks() {
        entityManager.persistAndFlush(STOCK);
        Stock stock = new Stock();
        stock.setName("Another stock");
        entityManager.persistAndFlush(stock);
        List<Stock> found = stockRepository.findAll();

        Assertions.assertThat(found).containsExactly(STOCK, stock);
    }

    @Test
    public void whenFindByName_thenReturnStock() {
        entityManager.persistAndFlush(STOCK);
        Stock found = stockRepository.findByName(STOCK_NAME).get();
        Assertions.assertThat(found.getName()).isEqualTo(STOCK_NAME);
    }

    @Test
    public void whenSave_thenReturnStock() {
        Stock saved = stockRepository.save(STOCK);
        Assertions.assertThat(saved.getName()).isEqualTo(STOCK.getName());
    }
}
