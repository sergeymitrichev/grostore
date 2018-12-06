package ru.ftob.grostore.service.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ftob.grostore.model.product.PriceRule;
import ru.ftob.grostore.model.product.PriceType;
import ru.ftob.grostore.persistence.price.PriceRuleRepository;
import ru.ftob.grostore.service.BaseService;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PriceRuleServiceImpl implements BaseService<PriceRule, Integer> {

    private final PriceRuleRepository repository;

    @Autowired
    public PriceRuleServiceImpl(PriceRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<PriceRule> get(Integer id) throws NotFoundException {
        return repository.findById(id);
    }

    public Optional<PriceRule> getByPriceType(PriceType priceType) {
        return repository.findByTypesContains(priceType);
    }

    @Override
    public PriceRule create(PriceRule priceRule) {
        return null;
    }

    @Override
    public PriceRule update(PriceRule priceRule) {
        return repository.save(priceRule);
    }

    @Override
    public Collection<PriceRule> updateAll(Collection<PriceRule> t) {
        return null;
    }

    @Override
    public Page<PriceRule> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        repository.deleteById(id);
    }

    @Override
    public List<PriceRule> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteAll(Collection<PriceRule> t) {

    }
}
