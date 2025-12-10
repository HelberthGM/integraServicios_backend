package com.lazarus.reservation.controller;

import com.lazarus.reservation.dto.recurrence.RecurrenceRequestDTO;
import com.lazarus.reservation.dto.recurrence.RecurrenceResponseDTO;
import com.lazarus.reservation.service.RecurrenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recurrences")
@RequiredArgsConstructor
public class RecurrenceController {

    private final RecurrenceService recurrenceService;

    @GetMapping
    public ResponseEntity<List<RecurrenceResponseDTO>> getAllRecurrences() {
        return ResponseEntity.ok(recurrenceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecurrenceResponseDTO> getRecurrenceById(@PathVariable UUID id) {
        return ResponseEntity.ok(recurrenceService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecurrenceResponseDTO>> getRecurrencesByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(recurrenceService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<RecurrenceResponseDTO> createRecurrence(@RequestBody @Valid RecurrenceRequestDTO dto) {
        RecurrenceResponseDTO response = recurrenceService.create(dto);
        URI location = URI.create("/api/recurrences/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecurrence(@PathVariable UUID id) {
        recurrenceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
