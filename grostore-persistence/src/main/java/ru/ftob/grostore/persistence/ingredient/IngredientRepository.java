package ru.ftob.grostore.persistence.ingredient;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import ru.ftob.grostore.model.ingredient.Ingredient;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Integer> {

    @Override
    @Transactional
    @NonNull
    <S extends Ingredient> List<S> saveAll(Iterable<S> entities);

    Optional<Ingredient> findByName(String name);
}
