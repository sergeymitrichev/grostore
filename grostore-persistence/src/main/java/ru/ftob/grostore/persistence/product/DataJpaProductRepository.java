package ru.ftob.grostore.persistence.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ftob.grostore.model.product.Product;

import java.util.List;

@Repository
public class DataJpaProductRepository implements ProductRepository{

    private final  CrudProductRepository crudRepository;

    @Autowired
    public DataJpaProductRepository(CrudProductRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Product save(Product product) {
        return crudRepository.save(product);
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return crudRepository.saveAll(products);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Product get(int id) {
        return crudRepository.getOne(id);
    }

    @Override
    public List<Product> getAll() {
        return crudRepository.findAll();
    }
}
