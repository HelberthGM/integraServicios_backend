package com.lazarus.resources.dto.resource;

import com.lazarus.resources.dto.location.ResourceLocationResponseDTO;
import com.lazarus.resources.dto.type.ResourceTypeResponseDTO;

import java.util.UUID;
import java.util.Map;

public record ResourceResponseDTO(
        UUID id,
        String code,
        String name,
        Map<String, Object> attributesJson,
        String photoUrl,
        Boolean active,
        ResourceLocationResponseDTO location,
        ResourceTypeResponseDTO type
) {}
