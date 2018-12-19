package ru.ftob.grostore.service.productlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.productlist.Category;
import ru.ftob.grostore.persistence.productlist.CategoryRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.ftob.grostore.service.util.ValidationUtil.checkNotFound;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Category> getAllRoot() {
        return repository.findAllByParent(null);
    }

    @Override
    public Category getByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NotFoundException("Category with name not found: " + name));
    }

    @Override
    public Category create(Category category) {
        Assert.notNull(category, "Category must not be null");
        Assert.isNull(category.getId(), "New category id must be null");
        return repository.save(category);
    }

    @Override
    public Category update(Category category) {
        Assert.notNull(category, "Category must not be null");
        if(category.isNew()) {
            Category saved = repository.findByName(category.getName()).orElse(null);
            checkNotFound(saved, "name = " + category.getName() + ". Can't update: detached entity doesn't exists");
            if(null != saved) {
                category.setId(saved.getId());
                category.setCreated(saved.getCreated());
                category.setCreatedBy(saved.getCreatedBy());
            }
        }
        return repository.save(category);
    }

    @Override
    public Collection<Category> updateAll(Collection<Category> t) {
        Assert.notNull(t, "Category list must not be null");
        Assert.notEmpty(t, "Category list must not be empty");
        List<Category> updated = new ArrayList<>();
        repository.saveAll(t).forEach(updated::add);
        return updated;
    }

    @Override
    public Category get(Integer id) throws NotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id not found: " + id));
    }

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Collection<Category> getAll() {
        List<Category> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        Assert.notNull(id, "Category id must not be null");
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(Collection<Category> t) {
        Assert.notNull(t, "Category list must not be null");
        Assert.notEmpty(t, "Category list must not be empty");
        repository.deleteAll(t);
    }
}
