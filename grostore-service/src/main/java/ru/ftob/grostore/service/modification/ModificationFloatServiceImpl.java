package ru.ftob.grostore.service.modification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.modification.ModificationFloat;
import ru.ftob.grostore.persistence.modification.ModificationFloatRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class ModificationFloatServiceImpl implements ModificationFloatService {

    private final ModificationFloatRepository repository;

    @Autowired
    public ModificationFloatServiceImpl(ModificationFloatRepository repository) {
        this.repository = repository;
    }

    @Override
    public ModificationFloat create(ModificationFloat modificationFloat) {
        Assert.notNull(modificationFloat, "modificationFloat must not be null");
        return repository.save(modificationFloat);
    }

    @Override
    public ModificationFloat update(ModificationFloat modificationFloat) {
        Assert.notNull(modificationFloat, "modificationFloat must not be null");
        return repository.save(modificationFloat);
    }

    @Override
    public Collection<ModificationFloat> updateAll(Collection<ModificationFloat> t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ModificationFloat get(Integer id) throws NotFoundException {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("modificationFloat with id not found: " + id));
    }

    @Override
    public Page<ModificationFloat> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Collection<ModificationFloat> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(Collection<ModificationFloat> t) {
        repository.deleteAll(t);
    }

    @Override
    public ModificationFloat getByName(String name) {
        return repository.findByName(name)
                .orElseThrow(
                        () -> new NotFoundException("modificationFloat with name not found: " + name));
    }
}
