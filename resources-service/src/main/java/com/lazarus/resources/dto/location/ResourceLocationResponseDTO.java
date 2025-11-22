package com.lazarus.resources.dto.location;

import java.util.UUID;

public record ResourceLocationResponseDTO(
        UUID id,
        String name,
        String campus,
        String building,
        String floor,
        String room,
        String description
) {}
