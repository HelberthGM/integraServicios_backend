package com.lazarus.resources.controller;

import com.lazarus.resources.model.ResourceLocation;
import com.lazarus.resources.service.ResourceLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/locations")
public class ResourceLocationController {

    private final ResourceLocationService service;

    public ResourceLocationController(ResourceLocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResourceLocation> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResourceLocation getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public ResourceLocation create(@RequestBody ResourceLocation location) {
        return service.create(location);
    }

    @PutMapping("/{id}")
    public ResourceLocation update(
            @PathVariable UUID id,
            @RequestBody ResourceLocation location
    ) {
        return service.update(id, location);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
