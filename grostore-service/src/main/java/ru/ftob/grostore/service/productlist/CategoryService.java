package ru.ftob.grostore.service.productlist;

import ru.ftob.grostore.model.productlist.Category;
import ru.ftob.grostore.service.BaseService;

import java.util.Collection;

public interface CategoryService extends BaseService<Category, Integer> {

    Collection<Category> getAllRoot();

    Category getByName(String name);
}
