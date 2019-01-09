package ru.ftob.grostore.model.ingredient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IngredientTest {
    private Ingredient ingredient;
    private final Integer INGREDIENT_ID = 10;
    private final String INGREDIENT_NAME = "Ingredient name";

    @BeforeEach
    void setUp() {
        ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setName(INGREDIENT_NAME);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assertEquals(ingredient.getName(), INGREDIENT_NAME);
    }

    @Test
    void setName() {
        String newName = "New Ingredient name";
        ingredient.setName(newName);
        assertEquals(ingredient.getName(), newName);
    }

    @Test
    void getId() {
        assertEquals(ingredient.getId(), INGREDIENT_ID);
    }

    @Test
    void isNew() {
        assertFalse(ingredient.isNew());

        Ingredient created = new Ingredient();
        assertTrue(created.isNew());
    }

    @Test
    void setId() {
        Integer newId = 20;
        ingredient.setId(newId);
        assertEquals(ingredient.getId(), newId);
    }
}