package com.lazarus.reservation.controller;

import com.lazarus.reservation.dto.CreateReservationRecurrenceDto;
import com.lazarus.reservation.dto.ReservationRecurrenceDto;
import com.lazarus.reservation.service.ReservationRecurrenceService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations/recurrences")
@RequiredArgsConstructor
public class ReservationRecurrenceController {

    private final ReservationRecurrenceService recurrenceService;

    @PostMapping
    public ResponseEntity<ReservationRecurrenceDto> createReservationRecurrence(
        @Valid @RequestBody CreateReservationRecurrenceDto createDto
    ) {
        ReservationRecurrenceDto recurrence =
            recurrenceService.createReservationRecurrence(createDto);
        return new ResponseEntity<>(recurrence, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<
        List<ReservationRecurrenceDto>
    > getAllReservationRecurrences() {
        List<ReservationRecurrenceDto> recurrences =
            recurrenceService.getAllReservationRecurrences();
        return ResponseEntity.ok(recurrences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<
        ReservationRecurrenceDto
    > getReservationRecurrenceById(@PathVariable UUID id) {
        ReservationRecurrenceDto recurrence =
            recurrenceService.getReservationRecurrenceById(id);
        return ResponseEntity.ok(recurrence);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<
        List<ReservationRecurrenceDto>
    > getReservationRecurrencesByUserId(@PathVariable String userId) {
        List<ReservationRecurrenceDto> recurrences =
            recurrenceService.getReservationRecurrencesByUserId(userId);
        return ResponseEntity.ok(recurrences);
    }
}
