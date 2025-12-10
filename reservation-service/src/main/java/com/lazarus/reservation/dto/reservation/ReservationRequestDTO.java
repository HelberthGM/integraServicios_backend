package com.lazarus.reservation.dto.reservation;

import com.lazarus.reservation.model.ReservationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ReservationRequestDTO(
        @NotBlank(message = "User ID is required")
        String userId,

        @NotNull(message = "Resource ID is required")
        UUID resourceId,

        @NotNull(message = "Resource type ID is required")
        UUID resourceTypeId,

        @NotNull(message = "Date is required")
        LocalDate dateLocal,

        @NotNull(message = "Start time is required")
        LocalTime startTime,

        @NotNull(message = "End time is required")
        LocalTime endTime,

        @NotNull(message = "Status is required")
        ReservationStatus status
) {
}
