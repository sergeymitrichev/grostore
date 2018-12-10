package ru.ftob.grostore.service.price;

import ru.ftob.grostore.model.product.PriceRule;
import ru.ftob.grostore.model.product.PriceType;
import ru.ftob.grostore.service.BaseService;

public interface PriceRuleService extends BaseService<PriceRule, Integer> {

    PriceRule getByPriceType(PriceType priceType);

}
