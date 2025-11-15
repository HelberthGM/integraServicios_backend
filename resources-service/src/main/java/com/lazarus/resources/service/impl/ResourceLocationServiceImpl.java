package com.lazarus.resources.service.impl;

import com.lazarus.resources.model.ResourceLocation;
import com.lazarus.resources.repository.ResourceLocationRepository;
import com.lazarus.resources.service.ResourceLocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResourceLocationServiceImpl implements ResourceLocationService {

    private final ResourceLocationRepository repo;

    public ResourceLocationServiceImpl(ResourceLocationRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ResourceLocation> findAll() {
        return repo.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public ResourceLocation findById(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    @Override
    public ResourceLocation create(ResourceLocation resourceLocation) {
        return repo.save(resourceLocation);
    }

    @SuppressWarnings("null")
    @Override
    public ResourceLocation update(UUID id, ResourceLocation data) {

        return repo.findById(id).map(l -> {

            l.setName(data.getName());
            l.setCampus(data.getCampus());
            l.setBuilding(data.getBuilding());
            l.setFloor(data.getFloor());
            l.setRoom(data.getRoom());
            l.setDescription(data.getDescription());

            return repo.save(l);

        }).orElse(null);
    }

    @SuppressWarnings("null")
    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}

