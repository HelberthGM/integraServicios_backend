package com.lazarus.auth.dto.user;

import com.lazarus.auth.model.UserRole;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String username,
        String fullName,
        UserRole role,
        Boolean active,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
