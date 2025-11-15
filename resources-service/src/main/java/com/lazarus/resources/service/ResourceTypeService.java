package com.lazarus.resources.service;

import com.lazarus.resources.model.ResourceType;
import java.util.List;
import java.util.UUID;

public interface ResourceTypeService {
    List<ResourceType> findAll();
    ResourceType findById(UUID id);
    ResourceType create(ResourceType type);
    ResourceType update(UUID id, ResourceType type);
    void delete(UUID id);
}

