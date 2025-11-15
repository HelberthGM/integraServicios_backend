package com.lazarus.resources.service.impl;

import com.lazarus.resources.service.ResourceService;
import com.lazarus.resources.repository.ResourceRepository;
import com.lazarus.resources.model.Resource;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository repo;

    public ResourceServiceImpl(ResourceRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Resource> findAll() {
        return repo.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public Resource findById(UUID id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Recurso no encontrado con ID: " + id));
    }

    @Override
    public Resource create(Resource resource) {
        if (resource == null) {
            throw new IllegalArgumentException("El recurso no puede ser nulo");
        }
        
        try {
            return repo.save(resource);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el recurso: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("null")
    @Override
    public Resource update(UUID id, Resource resource) {
        return repo.findById(id).map(existing -> {
            existing.setName(resource.getName());
            existing.setCode(resource.getCode());
            existing.setAttributesJson(resource.getAttributesJson());
            existing.setPhotoUrl(resource.getPhotoUrl());
            existing.setActive(resource.getActive());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Recurso no encontrado con ID: " + id));
    }

    @SuppressWarnings("null")
    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
