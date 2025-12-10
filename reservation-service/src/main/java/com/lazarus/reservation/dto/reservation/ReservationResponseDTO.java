package com.lazarus.reservation.dto.reservation;

import com.lazarus.reservation.model.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ReservationResponseDTO(
        UUID id,
        String userId,
        UUID resourceId,
        UUID resourceTypeId,
        LocalDate dateLocal,
        LocalTime startTime,
        LocalTime endTime,
        ReservationStatus status,
        OffsetDateTime createdAt
) {
}
