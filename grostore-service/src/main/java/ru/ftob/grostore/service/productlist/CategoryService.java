package ru.ftob.grostore.service.productlist;

import ru.ftob.grostore.model.productlist.Category;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.List;

public interface CategoryService {

    Category create(Category category);

    void delete(int id) throws NotFoundException;

    Category get(int id) throws NotFoundException;

    void update(Category category);

    void updateAll(List<Category> categories);

    List<Category> getAll();

    Category getByName(String name);
}
