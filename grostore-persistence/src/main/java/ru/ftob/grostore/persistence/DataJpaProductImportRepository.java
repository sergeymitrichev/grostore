package ru.ftob.grostore.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.ftob.grostore.model.product.ProductImport;

import java.util.List;

@Repository
public class DataJpaProductImportRepository implements ProductImportRepository {

    private static final Sort SORT_CREATED_NAME = new Sort(Sort.Direction.ASC, "created", "name");

    private final CrudProductImportRepository crudRepository;

    @Autowired
    public DataJpaProductImportRepository(CrudProductImportRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public ProductImport save(ProductImport productImport) {
        return crudRepository.save(productImport);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public ProductImport get(int id) {
        return crudRepository.getOne(id);
    }

    @Override
    public List<ProductImport> getAll() {
        return crudRepository.findAll(SORT_CREATED_NAME);
    }

    @Override
    public ProductImport getWithProducts(int id) {
        return crudRepository.getWithProducts(id);
    }
}
