package com.lazarus.reviews.service.impl;

import com.lazarus.reviews.dto.CreateRatingRequest;
import com.lazarus.reviews.dto.RatingResponse;
import com.lazarus.reviews.dto.UpdateRatingRequest;
import com.lazarus.reviews.exception.ResourceNotFoundException;
import com.lazarus.reviews.mapper.RatingMapper;
import com.lazarus.reviews.model.Rating;
import com.lazarus.reviews.repository.RatingRepository;
import com.lazarus.reviews.service.RatingService;
import com.lazarus.reviews.client.ResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final ResourceClient resourceClient; // <-- inyectamos Feign client

    @Override
    public RatingResponse createRating(CreateRatingRequest request) {

        // Validar que el recurso exista en el otro microservicio
        try {
            validateRatingValues(request);       // valores 0.0–5.0 e incrementos
            //validateResourceIsReturned(request); // el recurso debe estar devuelto
            validateNotDuplicate(request);       // no doble calificación
            resourceClient.getResourceById(request.getReservationId().toString());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Resource with ID " + request.getReservationId() + " does not exist");
        }

        // aseguramos unicidad por reservationId
        if (ratingRepository.existsByReservationId(request.getReservationId())) {
            throw new IllegalArgumentException("Rating for this reservation already exists");
        }

        Rating rating = new Rating();
        rating.setReservationId(request.getReservationId());
        rating.setServiceCompliance(BigDecimal.valueOf(request.getServiceCompliance()));
        rating.setResourceCondition(BigDecimal.valueOf(request.getResourceCondition()));
        rating.setStaffKindness(BigDecimal.valueOf(request.getStaffKindness()));

        Rating saved = ratingRepository.save(rating);

        return RatingMapper.toResponse(saved);
    }

    @SuppressWarnings("null")
    @Override
    public RatingResponse getRatingById(UUID id) {
        Rating rating = ratingRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found"));

        return RatingMapper.toResponse(rating);
    }

    @SuppressWarnings("null")
    @Override
    public RatingResponse updateRating(UUID id, UpdateRatingRequest request) {

        Rating rating = ratingRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found"));

        if (request.getServiceCompliance() != null)
            rating.setServiceCompliance(BigDecimal.valueOf(request.getServiceCompliance()));

        if (request.getResourceCondition() != null)
            rating.setResourceCondition(BigDecimal.valueOf(request.getResourceCondition()));

        if (request.getStaffKindness() != null)
            rating.setStaffKindness(BigDecimal.valueOf(request.getStaffKindness()));

        Rating updated = ratingRepository.save(rating);

        return RatingMapper.toResponse(updated);
    }

    @SuppressWarnings("null")
    @Override
    public void deleteRating(UUID id) {
        if (!ratingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rating not found");
        }
        ratingRepository.deleteById(id);
    }

    @Override
    public Double getGlobalAverage() {
        return ratingRepository.getGlobalAverageScore();
    }

    @Override
    public Double getAverageForReservation(UUID reservationId) {
        return ratingRepository.getAverageByReservation(reservationId);
    }

    @Override
    public boolean existsByReservationId(UUID reservationId) {
        return ratingRepository.existsByReservationId(reservationId);
    }

    @Override
    public RatingResponse getRatingByReservationId(UUID reservationId) {
        Rating rating = ratingRepository.findByReservationId(reservationId)
                .orElseThrow(() -> new RuntimeException("Rating not found for reservation " + reservationId));

        return RatingMapper.toResponse(rating);
    }

    private void validateRatingValues(CreateRatingRequest req) {

        double[] values = { req.getServiceCompliance(), req.getResourceCondition(), req.getStaffKindness() };

        for (double v : values) {

            if (v < 0.0 || v > 5.0) {
                throw new IllegalArgumentException("Rating values must be between 0.0 and 5.0");
            }

            if (!isStepValid(v)) {
                throw new IllegalArgumentException("Rating values must increase in steps of 0.1");
            }
        }
    }

    private boolean isStepValid(double v) {
        return Math.abs(v * 10 - Math.round(v * 10)) < 0.000001;
    }

    private void validateNotDuplicate(CreateRatingRequest req) {
    boolean exists = ratingRepository.existsByReservationId(req.getReservationId());

    if (exists) {
        throw new IllegalStateException("Resource has already been rated");
    }
}

}
