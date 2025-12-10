package com.lazarus.loan.dto;

import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

public record CreateLoanRequest(
        @NotNull UUID reservationId,
        @NotNull String employeeId,
        @NotNull ZonedDateTime plannedStart,
        @NotNull ZonedDateTime deliveredAt,
        String notes
) {}