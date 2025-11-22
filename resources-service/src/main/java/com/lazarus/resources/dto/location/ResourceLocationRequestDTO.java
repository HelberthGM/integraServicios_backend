package com.lazarus.resources.dto.location;

import java.util.UUID;

public record ResourceLocationRequestDTO(
        UUID id,          // null cuando es create
        String name,
        String campus,
        String building,
        String floor,
        String room,
        String description
) {}
