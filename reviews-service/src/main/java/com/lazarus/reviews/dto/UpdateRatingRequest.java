package com.lazarus.reviews.dto;

import jakarta.validation.constraints.*;

public class UpdateRatingRequest {

    @NotNull(message = "score is required")
    @DecimalMin(value = "0.0", message = "score must be at least 0.0")
    @DecimalMax(value = "5.0", message = "score must be at most 5.0")
    @Digits(integer = 1, fraction = 1, message = "score must have exactly one decimal place")
    private Double score;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
