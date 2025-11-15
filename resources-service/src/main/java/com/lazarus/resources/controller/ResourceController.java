package com.lazarus.resources.controller;

import com.lazarus.resources.model.Resource;
import com.lazarus.resources.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Resource> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Resource getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public Resource create(@RequestBody Resource resource) {
        return service.create(resource);
    }

    @PutMapping("/{id}")
    public Resource update(
            @PathVariable UUID id,
            @RequestBody Resource resource
    ) {
        return service.update(id, resource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
