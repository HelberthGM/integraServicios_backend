package com.lazarus.resources.service.impl;

import com.lazarus.resources.model.ResourceTypeAvailability;
import com.lazarus.resources.repository.ResourceTypeAvailabilityRepository;
import com.lazarus.resources.service.ResourceTypeAvailabilityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResourceTypeAvailabilityServiceImpl implements ResourceTypeAvailabilityService {

    private final ResourceTypeAvailabilityRepository repo;

    public ResourceTypeAvailabilityServiceImpl(ResourceTypeAvailabilityRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ResourceTypeAvailability> findAll() {
        return repo.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public ResourceTypeAvailability findById(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    @Override
    public ResourceTypeAvailability create(ResourceTypeAvailability availability) {
        return repo.save(availability);
    }

    @SuppressWarnings("null")
    @Override
    public ResourceTypeAvailability update(UUID id, ResourceTypeAvailability availability) {
        return repo.findById(id).map(existing -> {
            existing.setWeekday(availability.getWeekday());
            existing.setStartTime(availability.getStartTime());
            existing.setEndTime(availability.getEndTime());
            existing.setIsOpen(availability.getIsOpen());
            existing.setResourceType(availability.getResourceType());
            return repo.save(existing);
        }).orElse(null);
    }


    @SuppressWarnings("null")
    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public List<ResourceTypeAvailability> findByResourceTypeId(UUID resourceTypeId) {
        return repo.findByResourceType_Id(resourceTypeId);
    }
}

