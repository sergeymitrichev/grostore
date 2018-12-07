package ru.ftob.grostore.service.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ftob.grostore.model.product.PriceRule;
import ru.ftob.grostore.model.product.PriceType;
import ru.ftob.grostore.persistence.price.PriceRuleRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class PriceRuleServiceImpl implements PriceRuleService {

    private final PriceRuleRepository repository;

    @Autowired
    public PriceRuleServiceImpl(PriceRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public PriceRule get(Integer id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                "Not found price rule with id = " + id));
    }

    public PriceRule getByPriceType(PriceType priceType) {
        return repository.findByTypesContains(priceType)
                .orElseThrow(
                        () -> new NotFoundException(
                                "Not found price rule with [price type] = " + priceType));
    }

    @Override
    public PriceRule create(PriceRule priceRule) {
        return repository.save(priceRule);
    }

    @Override
    public PriceRule update(PriceRule priceRule) {
        return repository.save(priceRule);
    }

    @Override
    public Collection<PriceRule> updateAll(Collection<PriceRule> t) {
        Iterator<PriceRule> source = repository.saveAll(t).iterator();
        List<PriceRule> target = new ArrayList<>();
        source.forEachRemaining(target::add);
        return target;
    }

    @Override
    public Page<PriceRule> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        repository.deleteById(id);
    }

    @Override
    public Collection<PriceRule> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteAll(Collection<PriceRule> t) {
        repository.deleteAll(t);
    }
}
