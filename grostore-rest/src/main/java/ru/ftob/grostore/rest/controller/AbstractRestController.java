package ru.ftob.grostore.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ftob.grostore.rest.util.ModelMapperUtils;
import ru.ftob.grostore.service.BaseService;
import ru.ftob.grostore.service.util.exception.NotFoundException;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

//TODO add base validator for create/update
public class AbstractRestController<T, ID, G> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

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

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        log.info("Get all entities by pageable. " + pageable);
        return ResponseEntity.ok(ModelMapperUtils.mapPage(service.getAll(pageable), guiClass, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<G> get(@PathVariable ID id) {
        ResponseEntity<G> response = null;
        try {
            response = ResponseEntity.ok(ModelMapperUtils.map(service.get(id), guiClass));
            log.info("Get entity by id = " + id);
        } catch (NotFoundException e) {
            response = ResponseEntity.notFound().build();
            log.error(e.getMessage());
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            log.error(e.getMessage());
        }
        return response;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody G guiEntity) {
        T saved = service.create(ModelMapperUtils.map(guiEntity, dbClass));
        log.info("Entity created. " + saved);
        return ResponseEntity.ok(ModelMapperUtils.map(saved, guiClass));
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(
            @PathVariable ID id,
            @RequestBody G guiEntity
    ) {
        T updated = service.update(ModelMapperUtils.map(guiEntity, dbClass));
        ResponseEntity<G> response = ResponseEntity.ok(ModelMapperUtils.map(updated, guiClass));
        log.info("Entity with id = " + id + " updated. " + updated);
        return response;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateAll(
            @RequestBody List<G> guiEntities
    ) {
        List<T> updated = new ArrayList<>(service.updateAll(ModelMapperUtils.mapAll(guiEntities, dbClass)));
        log.info("Entities updated. " + updated);
        return ResponseEntity.ok(ModelMapperUtils.mapAll(updated, guiClass));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteAll(@RequestBody List<G> guiList) {
        List<T> dbList = ModelMapperUtils.mapAll(guiList, dbClass);
        service.deleteAll(dbList);
        log.info("Entities deleted. " + dbList);
        return ResponseEntity.ok().build();
    }
}
