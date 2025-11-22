package com.lazarus.reviews.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class RatingResponse {

    private UUID id;
    private UUID reservationId;
    private Double serviceCompliance;
    private Double resourceCondition;
    private Double staffKindness;
    private OffsetDateTime createdAt;

    public RatingResponse(
            UUID id,
            UUID reservationId,
            Double serviceCompliance,
            Double resourceCondition,
            Double staffKindness,
            OffsetDateTime createdAt
    ) {
        this.id = id;
        this.reservationId = reservationId;
        this.serviceCompliance = serviceCompliance;
        this.resourceCondition = resourceCondition;
        this.staffKindness = staffKindness;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public Double getServiceCompliance() {
        return serviceCompliance;
    }

    public Double getResourceCondition() {
        return resourceCondition;
    }

    public Double getStaffKindness() {
        return staffKindness;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
