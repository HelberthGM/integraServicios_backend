package com.lazarus.reservation.controller;

import com.lazarus.reservation.dto.reservation.ReservationRequestDTO;
import com.lazarus.reservation.dto.reservation.ReservationResponseDTO;
import com.lazarus.reservation.dto.reservation.ReservationUpdateDTO;
import com.lazarus.reservation.model.ReservationStatus;
import com.lazarus.reservation.service.ReservationService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(
        @Valid @RequestBody ReservationRequestDTO requestDTO
    ) {
        ReservationResponseDTO reservation = reservationService.create(
            requestDTO
        );
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations() {
        List<ReservationResponseDTO> reservations =
            reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(
        @PathVariable UUID id
    ) {
        ReservationResponseDTO reservation = reservationService.findById(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationsByUserId(
        @PathVariable String userId
    ) {
        List<ReservationResponseDTO> reservations =
            reservationService.findByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/resource/{resourceId}")
    public ResponseEntity<
        List<ReservationResponseDTO>
    > getReservationsByResourceId(@PathVariable UUID resourceId) {
        List<ReservationResponseDTO> reservations =
            reservationService.findByResourceId(resourceId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationsByStatus(
        @PathVariable ReservationStatus status
    ) {
        List<ReservationResponseDTO> reservations =
            reservationService.findByStatus(status);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/resource/{resourceId}/date/{date}")
    public ResponseEntity<
        List<ReservationResponseDTO>
    > getReservationsByResourceIdAndDate(
        @PathVariable UUID resourceId,
        @PathVariable LocalDate date
    ) {
        List<ReservationResponseDTO> reservations =
            reservationService.findByResourceIdAndDate(resourceId, date);
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> updateReservation(
        @PathVariable UUID id,
        @Valid @RequestBody ReservationUpdateDTO updateDTO
    ) {
        ReservationResponseDTO reservation = reservationService.update(
            id,
            updateDTO
        );
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<ReservationResponseDTO> confirmReservation(
        @PathVariable UUID id
    ) {
        ReservationResponseDTO reservation =
            reservationService.confirmReservation(id);
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponseDTO> cancelReservation(
        @PathVariable UUID id
    ) {
        ReservationResponseDTO reservation =
            reservationService.cancelReservation(id);
        return ResponseEntity.ok(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable UUID id) {
        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
