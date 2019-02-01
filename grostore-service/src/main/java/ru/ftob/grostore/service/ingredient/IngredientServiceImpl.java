package ru.ftob.grostore.service.ingredient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.ingredient.Ingredient;
import ru.ftob.grostore.persistence.ingredient.IngredientRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;

    public IngredientServiceImpl(IngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ingredient create(Ingredient ingredient) {
        Assert.notNull(ingredient, "Ingredient must not be null");
        return repository.save(ingredient);
    }

    @Override
    public Ingredient update(Ingredient Ingredient) {
        Assert.notNull(Ingredient, "Ingredient must not be null");
        return repository.save(Ingredient);
    }

    @Override
    public Collection<Ingredient> updateAll(Collection<Ingredient> t) {
        return repository.saveAll(t);
    }

    @Override
    public Ingredient get(Integer id) throws NotFoundException {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Ingredient with id not found: " + id));
    }

    @Override
    public Page<Ingredient> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Collection<Ingredient> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(Collection<Ingredient> t) {
        repository.deleteAll(t);
    }

    @Override
    public Ingredient getByName(String name) {
        return repository.findByName(name)
                .orElseThrow(
                        () -> new NotFoundException("Ingredient with name not found: " + name));
    }
}
