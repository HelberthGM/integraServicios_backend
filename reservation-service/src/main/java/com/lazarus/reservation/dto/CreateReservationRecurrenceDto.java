package com.lazarus.reservation.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class CreateReservationRecurrenceDto {

    @NotNull
    private String userId;

    @NotNull
    private UUID resourceTypeId;

    private UUID resourceId;

    @NotNull
    private Integer weekday;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;
}
