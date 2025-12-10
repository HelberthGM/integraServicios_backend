package com.lazarus.reservation.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class CreateReservationDto {

    @NotNull
    private String userId;

    @NotNull
    private UUID resourceId;

    @NotNull
    private UUID resourceTypeId;

    @NotNull
    private LocalDate dateLocal;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;
}
