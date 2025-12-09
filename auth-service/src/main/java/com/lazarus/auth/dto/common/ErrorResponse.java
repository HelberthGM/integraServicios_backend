package com.lazarus.auth.dto.common;

import java.time.OffsetDateTime;

public record ErrorResponse(
        String errorCode,
        String message,
        OffsetDateTime timestamp
) {
}
