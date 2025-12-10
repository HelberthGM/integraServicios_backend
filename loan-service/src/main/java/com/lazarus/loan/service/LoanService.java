package com.lazarus.loan.service;

import com.lazarus.loan.dto.CreateLoanRequest;
import com.lazarus.loan.dto.LoanResponse;
import com.lazarus.loan.model.Loan;
import com.lazarus.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoanService {

    private final LoanRepository repo;

    public LoanService(LoanRepository repo) {
        this.repo = repo;
    }

    // ----------------------------
    // CREATE
    // ----------------------------
    public LoanResponse createLoan(CreateLoanRequest req) {

        // Regla: una reserva → un solo préstamo
        if (repo.existsByReservationId(req.reservationId())) {
            throw new IllegalArgumentException("Loan already registered for this reservation");
        }

        Loan loan = new Loan();
        loan.setReservationId(req.reservationId());
        loan.setEmployeeId(req.employeeId());
        loan.setPlannedStart(req.plannedStart());
        loan.setDeliveredAt(req.deliveredAt());
        loan.setNotes(req.notes());

        // El trigger llena service_failure automáticamente
        Loan saved = repo.save(loan);

        return LoanResponse.fromEntity(saved);
    }

    // ----------------------------
    // READ ALL
    // ----------------------------
    public List<Loan> findAll() {
        return repo.findAll();
    }

    // ----------------------------
    // READ ONE
    // ----------------------------
    public Loan findById(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found: " + id));
    }

    // ----------------------------
    // UPDATE (solo campos permitidos)
    // ----------------------------
    public Loan update(UUID id, Loan updated) {
        Loan existing = findById(id);

        // Solo se pueden actualizar campos lógicos permitidos.
        existing.setEmployeeId(updated.getEmployeeId());
        existing.setDeliveredAt(updated.getDeliveredAt());
        existing.setNotes(updated.getNotes());

        // NO modificar:
        // plannedStart, reservationId, createdAt, serviceFailure

        return repo.save(existing);
    }

    // ----------------------------
    // DELETE
    // ----------------------------
    public void delete(UUID id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Loan not found: " + id);
        }
        repo.deleteById(id);
    }
}
