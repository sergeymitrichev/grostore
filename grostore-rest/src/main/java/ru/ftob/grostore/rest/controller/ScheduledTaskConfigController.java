package ru.ftob.grostore.rest.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ftob.grostore.model.ScheduledTaskConfig;
import ru.ftob.grostore.rest.webmodel.GuiScheduledTaskConfig;
import ru.ftob.grostore.service.ScheduledTaskConfigService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/tasks")
public class ScheduledTaskConfigController {

    private final ScheduledTaskConfigService scheduledTaskConfigService;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ScheduledTaskConfigController(ScheduledTaskConfigService scheduledTaskConfigService) {
        this.scheduledTaskConfigService = scheduledTaskConfigService;
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(Pageable pageable) {
        Page<ScheduledTaskConfig> tasks = scheduledTaskConfigService.getAll(pageable);
        List<GuiScheduledTaskConfig> guiProducts = tasks.stream().map(
                t -> modelMapper.map(t, GuiScheduledTaskConfig.class)
        ).collect(Collectors.toList());
        PageImpl page = new PageImpl(guiProducts, pageable, tasks.getTotalElements());

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return ResponseEntity.ok(scheduledTaskConfigService.get(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody GuiScheduledTaskConfig guiScheduledTaskConfig
    ) {
        return ResponseEntity.ok(modelMapper.map(scheduledTaskConfigService.create(modelMapper.map(guiScheduledTaskConfig, ScheduledTaskConfig.class)), GuiScheduledTaskConfig.class));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody GuiScheduledTaskConfig guiScheduledTaskConfig
    ) {
        scheduledTaskConfigService.update(modelMapper.map(guiScheduledTaskConfig, ScheduledTaskConfig.class));
        return ResponseEntity.ok(modelMapper.map(scheduledTaskConfigService.get(id), GuiScheduledTaskConfig.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        scheduledTaskConfigService.delete(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAll(@RequestBody List<GuiScheduledTaskConfig> guiScheduledTaskConfigs) {
        scheduledTaskConfigService.deleteAll(guiScheduledTaskConfigs.stream().map(g -> modelMapper.map(g, ScheduledTaskConfig.class)).collect(Collectors.toList()));
    }
}
