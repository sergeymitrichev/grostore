package ru.ftob.grostore.service.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ftob.grostore.model.product.*;
import ru.ftob.grostore.service.product.ProductService;
import ru.ftob.grostore.service.util.exception.InvalidPriceRuleException;

import java.util.*;

@Service
public class PriceServiceImpl implements PriceService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String PRICE_IN_TYPE_SUFFIX = "_IN";
    private static final String PRICE_OUT_TYPE_SUFFIX = "";

    private final ProductService productService;

    private final PriceRuleService priceRuleService;

    @Autowired
    public PriceServiceImpl(ProductService productService, PriceRuleService priceRuleService) {
        this.productService = productService;
        this.priceRuleService = priceRuleService;
    }

    @Override
    public void generateOutFromProducts(List<Product> products) {
        products.forEach(p -> {
            List<Price> prices = new ArrayList<>();
            p.getPrices().forEach(price -> {
                PriceType priceType = price.getType();
                if (priceType.toString().endsWith(PRICE_IN_TYPE_SUFFIX)) {
                    prices.add(price);
                    Optional<PriceRule> opt = priceRuleService.getByPriceType(price.getType());
                    if (opt.isPresent()) {
                        PriceRule priceRule = opt.get();
                        prices.add(new Price(
                                getValueInRows(priceRule.getRows(), price.getValue()),
                                PriceType.valueOf(priceType.toString().replace(PRICE_IN_TYPE_SUFFIX, PRICE_OUT_TYPE_SUFFIX)),
                                price.getConditionValue()
                        ));
                    }
                }
            });
            p.setPrices(prices);
        });
        productService.updateAll(products);
    }

    private Integer getValueInRows(Set<PriceRuleRow> rows, Integer in) throws InvalidPriceRuleException {
        for (PriceRuleRow r : rows) {
            if (in > r.getFrom() && in < r.getTo()) {
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
