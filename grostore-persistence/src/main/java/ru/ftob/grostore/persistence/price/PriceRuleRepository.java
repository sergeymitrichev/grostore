package ru.ftob.grostore.persistence.price;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.product.PriceRule;
import ru.ftob.grostore.model.product.PriceType;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface PriceRuleRepository extends PagingAndSortingRepository<PriceRule, Integer> {

    Optional<PriceRule> findByTypesContains(PriceType priceType);

    List<PriceRule> findAll();
}
