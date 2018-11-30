package ru.ftob.grostore.service.stock;

import ru.ftob.grostore.model.stock.Stock;
import ru.ftob.grostore.service.BaseService;
import ru.ftob.grostore.service.util.exception.NotFoundException;

public interface StockService extends BaseService<Stock, Integer> {

    Stock getByName(String name) throws NotFoundException;

    Stock getWithProducts(Integer id) throws NotFoundException;
}
