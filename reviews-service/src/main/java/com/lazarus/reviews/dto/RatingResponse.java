package com.lazarus.reviews.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class RatingResponse {

    private UUID id;
    private UUID reservationId;
    private Double score;
    private OffsetDateTime createdAt;

    public RatingResponse(UUID id, UUID reservationId, Double score, OffsetDateTime createdAt) {
        this.id = id;
        this.reservationId = reservationId;
        this.score = score;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public Double getScore() {
        return score;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
