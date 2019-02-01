package ru.ftob.grostore.service.modification;

import ru.ftob.grostore.model.modification.ModificationString;
import ru.ftob.grostore.service.BaseService;

public interface ModificationStringService extends BaseService<ModificationString, Integer> {

    ModificationString getByName(String name);
}
