package com.lazarus.resources.controller;

import com.lazarus.resources.model.ResourceTypeAvailability;
import com.lazarus.resources.service.ResourceTypeAvailabilityService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/availability")
public class ResourceTypeAvailabilityController {

    private final ResourceTypeAvailabilityService service;

    public ResourceTypeAvailabilityController(ResourceTypeAvailabilityService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResourceTypeAvailability> getAll() {
        return service.findAll();
    }

     @GetMapping("/type/{typeId}")
    public ResponseEntity<List<ResourceTypeAvailability>> getByType(@PathVariable UUID typeId) {
        return ResponseEntity.ok(service.findByResourceTypeId(typeId));
    }

    @GetMapping("/{id}")
    public ResourceTypeAvailability getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public ResourceTypeAvailability create(@RequestBody ResourceTypeAvailability a) {
        return service.create(a);
    }

    @PutMapping("/{id}")
    public ResourceTypeAvailability update(
            @PathVariable UUID id,
            @RequestBody ResourceTypeAvailability a
    ) {
        return service.update(id, a);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}

