package com.lazarus.loan.dto;


import java.time.ZonedDateTime;
import java.util.UUID;

public record LoanResponse(
        UUID id,
        UUID reservationId,
        String employeeId,
        ZonedDateTime plannedStart,
        ZonedDateTime deliveredAt,
        Boolean serviceFailure,
        String notes,
        ZonedDateTime createdAt
) {}