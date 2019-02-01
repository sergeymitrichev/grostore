package ru.ftob.grostore.service.ingredient;

import ru.ftob.grostore.model.ingredient.Ingredient;
import ru.ftob.grostore.service.BaseService;

public interface IngredientService extends BaseService<Ingredient, Integer> {

    Ingredient getByName(String name);
}
