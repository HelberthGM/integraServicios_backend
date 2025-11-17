package com.lazarus.resources.service;

import com.lazarus.resources.dto.resource.ResourceRequestDTO;
import com.lazarus.resources.dto.resource.ResourceResponseDTO;
import java.util.List;
import java.util.UUID;

public interface ResourceService {
    List<ResourceResponseDTO> findAll();
    ResourceResponseDTO findById(UUID id);
    ResourceResponseDTO create(ResourceRequestDTO dto);
    ResourceResponseDTO update(UUID id, ResourceRequestDTO dto);
    void delete(UUID id);
}
