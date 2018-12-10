package ru.ftob.grostore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ftob.grostore.model.product.PriceRule;
import ru.ftob.grostore.rest.webmodel.GuiPriceRule;
import ru.ftob.grostore.service.price.PriceRuleServiceImpl;
import ru.ftob.grostore.service.price.PriceService;

@RestController
@RequestMapping("/price-rules")
public class PriceRuleController extends AbstractRestController<PriceRule, Integer, GuiPriceRule> {

    private PriceRuleServiceImpl service;

    private PriceService priceService;

    @Autowired
    public PriceRuleController(PriceRuleServiceImpl service, PriceService priceService) {
        this.priceService = priceService;
        this.service = service;
        setService(service);
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculate() {
        priceService.generateOutFromAllProducts();
        return ResponseEntity.ok().build();
    }
}
