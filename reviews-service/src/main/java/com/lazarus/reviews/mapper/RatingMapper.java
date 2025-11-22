package com.lazarus.reviews.mapper;

import com.lazarus.reviews.dto.CreateRatingRequest;
import com.lazarus.reviews.dto.RatingResponse;
import com.lazarus.reviews.dto.UpdateRatingRequest;
import com.lazarus.reviews.model.Rating;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RatingMapper {

    private static final int DECIMALS = 1;

    private static BigDecimal toBigDecimal(Double value) {
        return BigDecimal
                .valueOf(value)
                .setScale(DECIMALS, RoundingMode.HALF_UP);
    }

    // -------- CREATE --------
    public static Rating toEntity(CreateRatingRequest request) {
        Rating rating = new Rating();

        rating.setReservationId(request.getReservationId());
        rating.setServiceCompliance(toBigDecimal(request.getServiceCompliance()));
        rating.setResourceCondition(toBigDecimal(request.getResourceCondition()));
        rating.setStaffKindness(toBigDecimal(request.getStaffKindness()));

        return rating;
    }

    // -------- UPDATE --------
    public static void updateEntity(Rating rating, UpdateRatingRequest request) {
        rating.setServiceCompliance(toBigDecimal(request.getServiceCompliance()));
        rating.setResourceCondition(toBigDecimal(request.getResourceCondition()));
        rating.setStaffKindness(toBigDecimal(request.getStaffKindness()));
    }

    // -------- RESPONSE --------
    public static RatingResponse toResponse(Rating rating) {
        return new RatingResponse(
                rating.getId(),
                rating.getReservationId(),
                rating.getServiceCompliance().doubleValue(),
                rating.getResourceCondition().doubleValue(),
                rating.getStaffKindness().doubleValue(),
                rating.getCreatedAt()
        );
    }
}
