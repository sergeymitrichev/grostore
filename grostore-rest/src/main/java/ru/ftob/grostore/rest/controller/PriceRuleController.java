package ru.ftob.grostore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ftob.grostore.model.product.PriceRule;
import ru.ftob.grostore.rest.webmodel.GuiPriceRule;

@RestController
@RequestMapping("/price-rules")
public class PriceRuleController extends AbstractRestController<PriceRule, Integer, GuiPriceRule> {
    private PriceRuleService service;

    @Autowired
    public StockController(PriceRuleService service) {
        this.service = service;
        setService(service);
    }
}
