package ru.ftob.grostore.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ftob.grostore.model.productlist.Category;
import ru.ftob.grostore.rest.util.ModelMapperUtils;
import ru.ftob.grostore.rest.webmodel.GuiCategory;
import ru.ftob.grostore.rest.webmodel.GuiCategorySimple;
import ru.ftob.grostore.rest.webmodel.GuiNodeCategory;
import ru.ftob.grostore.service.productlist.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractRestController<Category, Integer, GuiCategory> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
        setService(service);
    }

    @Override
    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok(
                ModelMapperUtils.mapPage(
                        service.getAll(pageable),
                        GuiCategorySimple.class,
                        pageable));
    }

    @GetMapping("/tree")
    public ResponseEntity<?> getTree() {
        List<GuiNodeCategory> categories = service.getAllRoot().stream().map(
                p -> ModelMapperUtils.map(p, GuiNodeCategory.class)
        ).collect(Collectors.toList());

        return ResponseEntity.ok(categories);
    }
}