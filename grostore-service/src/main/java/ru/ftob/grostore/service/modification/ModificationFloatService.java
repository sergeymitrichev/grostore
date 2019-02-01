package ru.ftob.grostore.service.modification;

import ru.ftob.grostore.model.modification.ModificationFloat;
import ru.ftob.grostore.service.BaseService;

public interface ModificationFloatService extends BaseService<ModificationFloat, Integer> {

    ModificationFloat getByName(String name);
}
