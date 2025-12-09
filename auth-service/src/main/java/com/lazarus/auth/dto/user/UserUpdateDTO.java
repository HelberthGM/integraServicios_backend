package com.lazarus.auth.dto.user;

import com.lazarus.auth.model.UserRole;

public record UserUpdateDTO(
        String fullName,
        UserRole role,
        Boolean active
) {
}
