package com.lazarus.resources.dto.resource;

import com.lazarus.resources.dto.common.IdOnlyDTO;
import java.util.Map;
import java.util.UUID;

public record ResourceRequestDTO(
        UUID id,
        String code,
        String name,
        Map<String, Object> attributesJson,
        String photoUrl,
        Boolean active,
        IdOnlyDTO location,
        IdOnlyDTO type
) {}
