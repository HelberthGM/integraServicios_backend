package com.lazarus.reservation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class ReservationRecurrenceDto {
    private UUID id;
    private String userId;
    private UUID resourceTypeId;
    private UUID resourceId;
    private Integer weekday;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
