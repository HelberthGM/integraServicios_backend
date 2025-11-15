package com.lazarus.resources.controller;

import com.lazarus.resources.model.ResourceType;
import com.lazarus.resources.service.ResourceTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/resource-types")
public class ResourceTypeController {

    private final ResourceTypeService service;

    public ResourceTypeController(ResourceTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResourceType> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResourceType getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public ResourceType create(@RequestBody ResourceType type) {
        return service.create(type);
    }

    @PutMapping("/{id}")
    public ResourceType update(
            @PathVariable UUID id,
            @RequestBody ResourceType type
    ) {
        return service.update(id, type);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
