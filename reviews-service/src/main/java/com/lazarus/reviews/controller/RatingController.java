package com.lazarus.reviews.controller;

import com.lazarus.reviews.dto.CreateRatingRequest;
import com.lazarus.reviews.dto.RatingResponse;
import com.lazarus.reviews.dto.UpdateRatingRequest;
import com.lazarus.reviews.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<RatingResponse> createRating(
            @RequestBody @Valid CreateRatingRequest request
    ) {
        RatingResponse response = ratingService.createRating(request);

        // Agregar Location header → /ratings/{id}
        URI location = URI.create("/ratings/" + response.getId());

        return ResponseEntity
                .created(location) // 201 Created
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingResponse> getRatingById(@PathVariable UUID id) {
        RatingResponse response = ratingService.getRatingById(id);
        return ResponseEntity.ok(response); // 200 OK
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<RatingResponse> getByReservationId(@PathVariable UUID reservationId) {
        RatingResponse response = ratingService.getRatingByReservationId(reservationId);
        return ResponseEntity.ok(response); // 200 OK
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingResponse> updateRating(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateRatingRequest request
    ) {
        RatingResponse updated = ratingService.updateRating(id, request);
        return ResponseEntity.ok(updated); // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable UUID id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/average")
    public ResponseEntity<Double> getGlobalAverage() {
        return ResponseEntity.ok(ratingService.getGlobalAverage()); // 200 OK
    }

    @GetMapping("/average/{reservationId}")
    public ResponseEntity<Double> getAverageByReservation(@PathVariable UUID reservationId) {
        return ResponseEntity.ok(ratingService.getAverageForReservation(reservationId)); // 200 OK
    }
}
