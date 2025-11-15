package com.lazarus.resources.service;

import com.lazarus.resources.model.Resource;
import java.util.List;
import java.util.UUID;

public interface ResourceService {
    List<Resource> findAll();
    Resource findById(UUID id);
    Resource create(Resource resource);
    Resource update(UUID id, Resource resource);
    void delete(UUID id);
}
