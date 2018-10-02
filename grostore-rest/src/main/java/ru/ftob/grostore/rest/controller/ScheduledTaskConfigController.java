package ru.ftob.grostore.rest.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ftob.grostore.model.ScheduledTaskConfig;
import ru.ftob.grostore.rest.webmodel.GuiScheduledTaskConfig;
import ru.ftob.grostore.service.ScheduledTaskConfigService;

import java.io.IOException;


@RestController
@RequestMapping("/schedule")
public class ScheduledTaskConfigController {

    private final ScheduledTaskConfigService scheduledTaskConfigService;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ScheduledTaskConfigController(ScheduledTaskConfigService scheduledTaskConfigService) {
        this.scheduledTaskConfigService = scheduledTaskConfigService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok(scheduledTaskConfigService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(scheduledTaskConfigService.get(id));
        }  catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody GuiScheduledTaskConfig guiScheduledTaskConfig
    ) {
        return ResponseEntity.ok(scheduledTaskConfigService.create(modelMapper.map(guiScheduledTaskConfig, ScheduledTaskConfig.class)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody GuiScheduledTaskConfig guiScheduledTaskConfig
    ) {
        scheduledTaskConfigService.update(modelMapper.map(guiScheduledTaskConfig, ScheduledTaskConfig.class));
        try {
            return ResponseEntity.ok(scheduledTaskConfigService.get(id));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        scheduledTaskConfigService.delete(id);
        return ResponseEntity.ok(id);
    }
}
