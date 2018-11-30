package ru.ftob.grostore.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.stock.Stock;
import ru.ftob.grostore.persistence.stock.StockRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

import static ru.ftob.grostore.service.util.ValidationUtil.checkNotFoundWithId;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock create(Stock stock) {
        Assert.notNull(stock, "Stock must not be null");
        return stockRepository.save(stock);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        checkNotFoundWithId(stockRepository.delete(id), id);
    }

    @Override
    public Stock get(Integer id) throws NotFoundException {
        return stockRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found entity with id = " + id));
    }

    @Override
    public Page<Stock> getAll(Pageable pageable) {
        return stockRepository.findAll(pageable);
    }

    @Override
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getByName(String name) throws NotFoundException {
        return stockRepository.findByName(name).orElseThrow(() -> new NotFoundException("Not found entity with name = " + name));
    }

    @Override
    public Stock getWithProducts(Integer id) throws NotFoundException {
        return stockRepository.getWithProducts(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Stock update(Stock stock) {
        Assert.notNull(stock, "Stock must not be null");
        return checkNotFoundWithId(stockRepository.save(stock), stock.getId());
    }

    @Override
    public Collection<Stock> updateAll(Collection<Stock> t) {
        return stockRepository.saveAll(t);
    }

    @Override
    public void deleteAll(Collection<Stock> t) {
        stockRepository.deleteAll(t);
    }
}
