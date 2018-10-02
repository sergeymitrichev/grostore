package ru.ftob.grostore.service.productlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.productlist.Category;
import ru.ftob.grostore.persistence.productlist.CategoryRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;
import ru.ftob.grostore.service.validator.ProductValidator;

import java.util.List;

import static ru.ftob.grostore.service.util.ValidationUtil.checkNotFoundWithId;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private static final int ROOT_CATEGORY_ID = 1;

    @Autowired
    @Qualifier("productValidator")
    private ProductValidator validator;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public Category create(Category category) {
        Assert.notNull(category, "Product must not be null");
        return repository.save(category);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Category get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.getOne(id), id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Category category) {
        Assert.notNull(category, "Product must not be null");
        checkNotFoundWithId(repository.save(category), category.getId());
    }

    @Override
    public void updateAll(List<Category> categories) {
        Assert.notNull(categories, "Product list must not be null");
        repository.saveAll(categories);
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Category> getAllRoot() {
        return repository.findAllByParent(repository.getOne(ROOT_CATEGORY_ID));
    }

    @Override
    public Category getByName(String name) {
        Assert.notNull(name, "Category name must not be null");
        return repository.getByName(name);
    }
}
