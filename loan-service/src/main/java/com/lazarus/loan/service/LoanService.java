package com.lazarus.loan.service;

import com.lazarus.loan.dto.CreateLoanRequest;
import com.lazarus.loan.dto.LoanResponse;
import com.lazarus.loan.model.Loan;
import com.lazarus.loan.repository.LoanRepository;

import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final LoanRepository repo;

    public LoanService(LoanRepository repo) {
        this.repo = repo;
    }

    public LoanResponse createLoan(CreateLoanRequest req) {

        // Cada reserva solo puede tener un préstamo
        if (repo.existsByReservationId(req.reservationId())) {
            throw new IllegalArgumentException("Loan already registered for this reservation");
        }

        Loan loan = new Loan();
        loan.setReservationId(req.reservationId());
        loan.setEmployeeId(req.employeeId());
        loan.setPlannedStart(req.plannedStart());
        loan.setDeliveredAt(req.deliveredAt());
        loan.setNotes(req.notes());

        Loan saved = repo.save(loan);

        return new LoanResponse(
                saved.getId(),
                saved.getReservationId(),
                saved.getEmployeeId(),
                saved.getPlannedStart(),
                saved.getDeliveredAt(),
                saved.getServiceFailure(),
                saved.getNotes(),
                saved.getCreatedAt()
        );
    }
}

