package com.lazarus.resources.controller;

import com.lazarus.resources.dto.resource.ResourceRequestDTO;
import com.lazarus.resources.dto.resource.ResourceResponseDTO;
import com.lazarus.resources.service.ResourceService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping
    public ResponseEntity<List<ResourceResponseDTO>> getAll() {
        return ResponseEntity.ok(resourceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(resourceService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ResourceResponseDTO> create(@RequestBody ResourceRequestDTO dto) {
        return ResponseEntity.ok(resourceService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody ResourceRequestDTO dto
    ) {
        return ResponseEntity.ok(resourceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        resourceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
