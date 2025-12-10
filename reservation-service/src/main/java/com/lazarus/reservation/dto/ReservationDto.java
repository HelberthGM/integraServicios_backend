package com.lazarus.reservation.dto;

import com.lazarus.reservation.model.ReservationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class ReservationDto {
    private UUID id;
    private String userId;
    private UUID resourceId;
    private UUID resourceTypeId;
    private LocalDate dateLocal;
    private LocalTime startTime;
    private LocalTime endTime;
    private ReservationStatus status;
}
