package com.lazarus.reviews.repository;

import com.lazarus.reviews.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {

    Optional<Rating> findByReservationId(UUID reservationId);

    boolean existsByReservationId(UUID reservationId);

    @Query("SELECT AVG(r.score) FROM Rating r")
    Double getGlobalAverageScore();

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.reservationId = :reservationId")
    Double getAverageByReservation(UUID reservationId);
}
