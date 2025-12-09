package com.lazarus.auth.dto.auth;

import com.lazarus.auth.model.UserRole;

import java.util.UUID;

public record LoginResponse(
        UUID userId,
        String username,
        String fullName,
        UserRole role,
        String message
) {
}
