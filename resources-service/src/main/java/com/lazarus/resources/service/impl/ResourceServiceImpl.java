package com.lazarus.resources.service.impl;

import com.lazarus.resources.dto.resource.ResourceRequestDTO;
import com.lazarus.resources.dto.resource.ResourceResponseDTO;
import com.lazarus.resources.mapper.ResourceMapper;
import com.lazarus.resources.model.Resource;
import com.lazarus.resources.repository.ResourceRepository;
import com.lazarus.resources.service.ResourceService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    // -----------------------------------------
    // GET ALL
    // -----------------------------------------
    @Override
    public List<ResourceResponseDTO> findAll() {
        return resourceRepository.findAll().stream()
                .map(resourceMapper::toDTO)
                .toList();
    }

    // -----------------------------------------
    // GET BY ID
    // -----------------------------------------
    @SuppressWarnings("null")
    @Override
    public ResourceResponseDTO findById(UUID id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        return resourceMapper.toDTO(resource);
    }

    // -----------------------------------------
    // CREATE
    // -----------------------------------------
    @SuppressWarnings("null")
    @Override
    public ResourceResponseDTO create(ResourceRequestDTO dto) {
        Resource entity = resourceMapper.toEntity(dto);
        Resource saved = resourceRepository.save(entity);
        return resourceMapper.toDTO(saved);
    }

    // -----------------------------------------
    // UPDATE
    // -----------------------------------------
    @SuppressWarnings("null")
    @Override
    public ResourceResponseDTO update(UUID id, ResourceRequestDTO dto) {
        Resource existing = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        Resource updated = resourceMapper.toEntity(dto);
        updated.setId(existing.getId());

        Resource saved = resourceRepository.save(updated);
        return resourceMapper.toDTO(saved);
    }

    // -----------------------------------------
    // DELETE
    // -----------------------------------------
    @SuppressWarnings("null")
    @Override
    public void delete(UUID id) {
        if (!resourceRepository.existsById(id)) {
            throw new RuntimeException("Resource not found");
        }
        resourceRepository.deleteById(id);
    }
}

