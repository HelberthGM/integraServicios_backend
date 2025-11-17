package com.lazarus.resources.dto.type;

import java.util.UUID;
import java.time.LocalTime;

public record ResourceTypeAvailabilityResponseDTO(
        UUID id,
        Integer weekday,
        LocalTime startTime,
        LocalTime endTime,
        Boolean isOpen,
        ResourceTypeResponseDTO resourceType
) {}
