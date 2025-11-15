package com.lazarus.resources.service.impl;

import com.lazarus.resources.model.ResourceType;
import com.lazarus.resources.repository.ResourceTypeRepository;
import com.lazarus.resources.service.ResourceTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {

    private final ResourceTypeRepository repo;

    public ResourceTypeServiceImpl(ResourceTypeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ResourceType> findAll() {
        return repo.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public ResourceType findById(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    @Override
    public ResourceType create(ResourceType type) {
        return repo.save(type);
    }

    @SuppressWarnings("null")
    @Override
    public ResourceType update(UUID id, ResourceType type) {
        return repo.findById(id).map(t -> {
            t.setName(type.getName());
            t.setCode(type.getCode());
            t.setDescription(type.getDescription());
            return repo.save(t);
        }).orElse(null);
    }

    @SuppressWarnings("null")
    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
