package com.lazarus.resources.dto.type;

import java.time.LocalTime;
import java.util.UUID;

import com.lazarus.resources.dto.common.IdOnlyDTO;

public record ResourceTypeAvailabilityRequestDTO(
        UUID id,
        Integer weekday,
        LocalTime startTime,
        LocalTime endTime,
        Boolean isOpen,
        IdOnlyDTO resourceType
) {}
