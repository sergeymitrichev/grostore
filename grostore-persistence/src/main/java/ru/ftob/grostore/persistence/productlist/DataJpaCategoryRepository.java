package ru.ftob.grostore.persistence.productlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ftob.grostore.model.productlist.Category;

import java.util.List;

@Repository
public class DataJpaCategoryRepository implements CategoryRepository{

    private final  CrudCategoryRepository crudRepository;

    @Autowired
    public DataJpaCategoryRepository(CrudCategoryRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Category save(Category category) {
        return crudRepository.save(category);
    }

    @Override
    public List<Category> saveAll(List<Category> categories) {
        return crudRepository.saveAll(categories);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Category get(int id) {
        return crudRepository.getOne(id);
    }

    @Override
    public List<Category> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public Category getByName(String name) {
        return crudRepository.getByName(name);
    }
}
