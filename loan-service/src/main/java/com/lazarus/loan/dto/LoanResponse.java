package com.lazarus.loan.dto;

import com.lazarus.loan.model.Loan;
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
) {

    // Método estático para construir desde la entidad Loan
    public static LoanResponse fromEntity(Loan loan) {
        return new LoanResponse(
                loan.getId(),
                loan.getReservationId(),
                loan.getEmployeeId(),
                loan.getPlannedStart(),
                loan.getDeliveredAt(),
                loan.getServiceFailure(),
                loan.getNotes(),
                loan.getCreatedAt()
        );
    }
}
