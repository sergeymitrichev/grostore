package ru.ftob.grostore.service.price;

import ru.ftob.grostore.model.product.PriceRule;
import ru.ftob.grostore.model.product.PriceType;

import java.util.List;
import java.util.Optional;

public interface PriceRuleService {

    Optional<PriceRule> get(int id);

    Optional<PriceRule> getByPriceType(PriceType priceType);

    void update(PriceRule priceRule);

    void delete(int id);

    List<PriceRule> getAll();

}
