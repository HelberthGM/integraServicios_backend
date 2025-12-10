package com.lazarus.loan.controller;

import com.lazarus.loan.dto.CreateLoanRequest;
import com.lazarus.loan.dto.LoanResponse;
import com.lazarus.loan.model.Loan;
import com.lazarus.loan.repository.LoanRepository;
import com.lazarus.loan.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService service;
    private final LoanRepository repo;

    public LoanController(LoanService service, LoanRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@RequestBody CreateLoanRequest req) {
        return ResponseEntity.ok(service.createLoan(req));
    }

    @GetMapping
    public List<LoanResponse> findAll() {
        return repo.findAll().stream()
                .map(l -> new LoanResponse(
                        l.getId(),
                        l.getReservationId(),
                        l.getEmployeeId(),
                        l.getPlannedStart(),
                        l.getDeliveredAt(),
                        l.getServiceFailure(),
                        l.getNotes(),
                        l.getCreatedAt()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> findById(@PathVariable UUID id) {
        return repo.findById(id)
                .map(l -> new LoanResponse(
                        l.getId(),
                        l.getReservationId(),
                        l.getEmployeeId(),
                        l.getPlannedStart(),
                        l.getDeliveredAt(),
                        l.getServiceFailure(),
                        l.getNotes(),
                        l.getCreatedAt()
                ))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-reservation/{reservationId}")
    public ResponseEntity<LoanResponse> findByReservation(
            @PathVariable UUID reservationId
    ) {
        return repo.findByReservationId(reservationId)
                .map(l -> new LoanResponse(
                        l.getId(),
                        l.getReservationId(),
                        l.getEmployeeId(),
                        l.getPlannedStart(),
                        l.getDeliveredAt(),
                        l.getServiceFailure(),
                        l.getNotes(),
                        l.getCreatedAt()
                ))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

     @PutMapping("/{id}")
    public ResponseEntity<Loan> update(@PathVariable UUID id, @RequestBody Loan loan) {
        return ResponseEntity.ok(service.update(id, loan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
