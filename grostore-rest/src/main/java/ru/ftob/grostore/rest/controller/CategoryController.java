package ru.ftob.grostore.rest.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ftob.grostore.model.productlist.Category;
import ru.ftob.grostore.rest.webmodel.GuiCategory;
import ru.ftob.grostore.rest.webmodel.GuiNodeCategory;
import ru.ftob.grostore.service.productlist.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
        modelMapper = new ModelMapper();
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<GuiCategory> categories = categoryService.getAll().stream().map(
                p -> modelMapper.map(p, GuiCategory.class)
        ).collect(Collectors.toList());

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/tree")
    public ResponseEntity<?> getTree() {
        List<GuiNodeCategory> categories = categoryService.getAllRoot().stream().map(
                p -> modelMapper.map(p, GuiNodeCategory.class)
        ).collect(Collectors.toList());

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.get(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Category category) {
        categoryService.create(category);
        return ResponseEntity.ok(categoryService.getAllRoot().stream().map(
                p -> modelMapper.map(p, GuiNodeCategory.class)
        ).collect(Collectors.toList()));
    }

    @PostMapping("/tree")
    public ResponseEntity<?> saveAll(@RequestBody List<Category> categories) {
        categoryService.updateAll(categories);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody Category category
    ) {
        categoryService.update(category);
        //TODO move to util or converter class
        return ResponseEntity.ok(categoryService.getAllRoot().stream().map(
                p -> modelMapper.map(p, GuiNodeCategory.class)
        ).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.ok(id);
    }
}