package com.lazarus.reviews.service;

import com.lazarus.reviews.dto.CreateRatingRequest;
import com.lazarus.reviews.dto.RatingResponse;
import com.lazarus.reviews.dto.UpdateRatingRequest;

import java.util.UUID;

public interface RatingService {

    RatingResponse createRating(CreateRatingRequest request);

    RatingResponse getRatingById(UUID id);

    RatingResponse updateRating(UUID id, UpdateRatingRequest request);

    void deleteRating(UUID id);

    Double getGlobalAverage();

    Double getAverageForReservation(UUID reservationId);

    boolean existsByReservationId(UUID reservationId);
    
    RatingResponse getRatingByReservationId(UUID reservationId);
}
