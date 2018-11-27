package ru.ftob.grostore.rest.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ftob.grostore.rest.util.ModelMapperUtils;
import ru.ftob.grostore.service.BaseService;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractRestController<T, ID, G> {

    private Class<G> guiClass;

    private Class<T> dbClass;

    private BaseService<T, ID> service;

    public AbstractRestController() {
        this.dbClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.guiClass = (Class<G>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[2];
    }

    public void setService(BaseService<T, ID> service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok(ModelMapperUtils.mapPage(service.getAll(pageable), guiClass, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable ID id) {
        return ResponseEntity.ok(ModelMapperUtils.map(service.get(id), guiClass));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody G guiEntity) {
        T dbEntity = ModelMapperUtils.map(guiEntity, dbClass);
        return ResponseEntity.ok(ModelMapperUtils.map(service.create(dbEntity), guiClass));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable ID id,
            @RequestBody G guiEntity
    ) {
        T dbEntity = service.update(ModelMapperUtils.map(guiEntity, dbClass));
        return ResponseEntity.ok(ModelMapperUtils.map(dbEntity, guiClass));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll(@RequestBody List<G> guiList) {
        List<T> dbList = ModelMapperUtils.mapAll(guiList, dbClass);
        service.deleteAll(dbList);
        return ResponseEntity.ok().build();
    }
}
