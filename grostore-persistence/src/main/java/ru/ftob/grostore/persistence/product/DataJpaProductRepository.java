package ru.ftob.grostore.persistence.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.ftob.grostore.model.account.Account;
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
    public void deleteAll(List<Product> products) {
        crudRepository.deleteAll(products);
    }

    @Override
    public Product get(int id) {
        return crudRepository.findById(id).get();
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return crudRepository.findAll(pageable);
    }

    @Override
    public Product getBySku(String sku) {
        return crudRepository.getBySku(sku);
    }

    @Override
    public List<Product> getAllBySku(List<String> sku) {
        return crudRepository.findAllBySku(sku);
    }

    @Override
    public List<Product> getAllByUpdatedBy(Account updatedBy) {
        return crudRepository.findAllByUpdatedBy(updatedBy);
    }

    @Override
    public List<Product> getAll() {
        return crudRepository.findAll();
    }
}
