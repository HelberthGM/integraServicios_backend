package com.lazarus.reservation.dto.recurrence;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

public record RecurrenceResponseDTO(
        UUID id,
        String userId,
        UUID resourceTypeId,
        UUID resourceId,
        Integer weekday,
        LocalDate startDate,
        LocalDate endDate,
        LocalTime startTime,
        LocalTime endTime,
        OffsetDateTime createdAt
) {
}
