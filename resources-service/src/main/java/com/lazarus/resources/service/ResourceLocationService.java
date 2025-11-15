package com.lazarus.resources.service;

import com.lazarus.resources.model.ResourceLocation;
import java.util.List;
import java.util.UUID;

public interface ResourceLocationService {
    List<ResourceLocation> findAll();
    ResourceLocation findById(UUID id);
    ResourceLocation create(ResourceLocation location);
    ResourceLocation update(UUID id, ResourceLocation location);
    void delete(UUID id);
}
