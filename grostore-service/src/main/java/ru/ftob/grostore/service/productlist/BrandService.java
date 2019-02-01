package ru.ftob.grostore.service.productlist;

import ru.ftob.grostore.model.productlist.Brand;
import ru.ftob.grostore.service.BaseService;

public interface BrandService extends BaseService<Brand, Integer> {

    Brand getByName(String name);
}
