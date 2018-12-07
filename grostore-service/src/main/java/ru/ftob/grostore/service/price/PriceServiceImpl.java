package ru.ftob.grostore.service.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ftob.grostore.model.product.*;
import ru.ftob.grostore.service.product.ProductService;
import ru.ftob.grostore.service.util.exception.InvalidPriceRuleException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceServiceImpl implements PriceService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String PRICE_IN_TYPE_SUFFIX = "_IN";
    private static final String PRICE_OUT_TYPE_SUFFIX = "_SOLD";

    private final ProductService productService;

    private final PriceRuleService priceRuleService;

    @Autowired
    public PriceServiceImpl(ProductService productService, PriceRuleService priceRuleService) {
        this.productService = productService;
        this.priceRuleService = priceRuleService;
    }

    @Override
    public void generateOutFromAllProducts() {
        generateOutFromProducts(productService.getAll());
    }

    @Override
    public void generateOutFromProducts(List<Product> products) {

        Map<PriceType, PriceRule> rules = getRulesMap();

        products.forEach(p -> {
            List<Price> prices = new ArrayList<>();
            p.getPrices().forEach(price -> {
                PriceType priceType = price.getType();
                if (priceType.toString().endsWith(PRICE_IN_TYPE_SUFFIX)) {

                    try {
                        prices.add(price);
                        PriceRule priceRule = rules.get(price.getType());
                        if(priceRule != null) {
                            Integer value = getValueInRows(priceRule.getRows(), price.getValue());
                            prices.add(new Price(
                                    value,
                                    PriceType.valueOf(priceType.toString().replace(PRICE_IN_TYPE_SUFFIX, PRICE_OUT_TYPE_SUFFIX)),
                                    price.getConditionValue()
                            ));
                        } else {
                            log.warn("Price rule not found for " + price.getType() + ". Sold price for this type will not be created.");
                        }
                    } catch (InvalidPriceRuleException e) {
                        log.error(e.getMessage() + ". Out price will be equals income price.", e);
                    }
                }
            });
            p.setPrices(prices);
        });
        productService.updateAll(products);
    }

    private Map<PriceType, PriceRule> getRulesMap() {
        Map<PriceType, PriceRule> map = new HashMap<>();
        priceRuleService.getAll().forEach(r -> {
            r.getTypes().forEach(t -> {
                map.put(t, r);
                if(map.containsKey(t) || map.get(t) != null) {
                    log.warn("Non unique price rules founded for price type: " + t);
                }
            });
        });
        return map;
    }

    private Integer getValueInRows(List<PriceRuleRow> rows, Integer in) throws InvalidPriceRuleException {
        for (PriceRuleRow r : rows) {
            if (in >= r.getFrom() && in < r.getTo()) {
                Integer value = in;
                switch (r.getType()) {
                    case PRICE_RULE_ABSOLUTE: {
                        value += r.getValue();
                        return value;
                    }
                    case PRICE_RULE_RELATIVE: {
                        value += value * r.getValue() / 100;
                        return value;
                    }
                }
            }
        }
        throw new InvalidPriceRuleException("Cannot get value from price rule rows");
    }
}
