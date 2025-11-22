package com.lazarus.reviews.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class ErrorResponse {
    private String errorCode;
    private String message;
    private OffsetDateTime timestamp;
}
