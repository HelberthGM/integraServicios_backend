package com.lazarus.reservation.dto.recurrence;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record RecurrenceRequestDTO(
        @NotBlank(message = "User ID is required")
        String userId,

        @NotNull(message = "Resource type ID is required")
        UUID resourceTypeId,

        UUID resourceId,

        @NotNull(message = "Weekday is required")
        @Min(value = 0, message = "Weekday must be between 0 and 6")
        @Max(value = 6, message = "Weekday must be between 0 and 6")
        Integer weekday,

        @NotNull(message = "Start date is required")
        LocalDate startDate,

        @NotNull(message = "End date is required")
        LocalDate endDate,

        @NotNull(message = "Start time is required")
        LocalTime startTime,

        @NotNull(message = "End time is required")
        LocalTime endTime
) {
}
