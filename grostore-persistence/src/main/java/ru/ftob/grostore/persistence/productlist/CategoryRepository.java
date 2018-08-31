package ru.ftob.grostore.persistence.productlist;

import ru.ftob.grostore.model.productlist.Category;

import java.util.List;

public interface CategoryRepository {

    Category save(Category category);

    List<Category> saveAll(List<Category> categories);

    boolean delete(int id);

    Category get(int id);

    List<Category> getAll();

    Category getByName(String name);
}
