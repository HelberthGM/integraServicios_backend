package com.lazarus.resources.service;

import com.lazarus.resources.model.ResourceTypeAvailability;
import java.util.List;
import java.util.UUID;

public interface ResourceTypeAvailabilityService {
    List<ResourceTypeAvailability> findAll();
    ResourceTypeAvailability findById(UUID id);
    ResourceTypeAvailability create(ResourceTypeAvailability availability);
    ResourceTypeAvailability update(UUID id, ResourceTypeAvailability availability);
    void delete(UUID id);
    List<ResourceTypeAvailability> findByResourceTypeId(UUID resourceTypeId);
}
