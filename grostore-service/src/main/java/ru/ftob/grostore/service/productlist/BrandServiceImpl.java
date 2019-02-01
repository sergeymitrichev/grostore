package ru.ftob.grostore.service.productlist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.productlist.Brand;
import ru.ftob.grostore.persistence.productlist.BrandRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;

    public BrandServiceImpl(BrandRepository repository) {
        this.repository = repository;
    }

    @Override
    public Brand create(Brand brand) {
        Assert.notNull(brand, "Brand must not be null");
        return repository.save(brand);
    }

    @Override
    public Brand update(Brand brand) {
        Assert.notNull(brand, "Brand must not be null");
        return repository.save(brand);
    }

    @Override
    public Collection<Brand> updateAll(Collection<Brand> t) {
        return repository.saveAll(t);
    }

    @Override
    public Brand get(Integer id) throws NotFoundException {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Brand with id not found: " + id));
    }

    @Override
    public Page<Brand> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Collection<Brand> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(Collection<Brand> t) {
        repository.deleteAll(t);
    }

    @Override
    public Brand getByName(String name) {
        return repository.findByName(name)
                .orElseThrow(
                        () -> new NotFoundException("Brand with name not found: " + name));
    }
}
