package ru.ftob.grostore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ftob.grostore.model.stock.Stock;
import ru.ftob.grostore.rest.util.ModelMapperUtils;
import ru.ftob.grostore.rest.webmodel.GuiStock;
import ru.ftob.grostore.rest.webmodel.GuiStockWithProducts;
import ru.ftob.grostore.service.stock.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController extends AbstractRestController<Stock, Integer, GuiStock> {

    private StockService service;

    @Autowired
    public StockController(StockService stockService) {
        service = stockService;
        setService(stockService);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return ResponseEntity.ok(ModelMapperUtils.map(service.getWithProducts(id), GuiStockWithProducts.class));
    }

    /*@GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<Stock> stockList = stockService.getAll();
        List<GuiStock> guiStockList = stockList
                .stream()
                .map(s -> getMapper().map(s, GuiStock.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(guiStockList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return ResponseEntity.ok(stockService.get(id));
    }*/


}
