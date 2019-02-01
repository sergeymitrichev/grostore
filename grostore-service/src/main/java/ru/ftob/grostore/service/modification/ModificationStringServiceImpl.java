package ru.ftob.grostore.service.modification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ftob.grostore.model.modification.ModificationString;
import ru.ftob.grostore.persistence.modification.ModificationStringRepository;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class ModificationStringServiceImpl implements ModificationStringService {

    private final ModificationStringRepository repository;

    @Autowired
    public ModificationStringServiceImpl(ModificationStringRepository repository) {
        this.repository = repository;
    }

    @Override
    public ModificationString create(ModificationString modificationString) {
        Assert.notNull(modificationString, "ModificationString must not be null");
        return repository.save(modificationString);
    }

    @Override
    public ModificationString update(ModificationString modificationString) {
        Assert.notNull(modificationString, "ModificationString must not be null");
        return repository.save(modificationString);
    }

    @Override
    public Collection<ModificationString> updateAll(Collection<ModificationString> t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ModificationString get(Integer id) throws NotFoundException {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("ModificationString with id not found: " + id));
    }

    @Override
    public Page<ModificationString> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Collection<ModificationString> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(Collection<ModificationString> t) {
        repository.deleteAll(t);
    }

    @Override
    public ModificationString getByName(String name) {
        return repository.findByName(name)
                .orElseThrow(
                        () -> new NotFoundException("ModificationString with name not found: " + name));
    }
}
