package com.lazarus.reviews.repository;

import com.lazarus.reviews.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {

    Optional<Rating> findByReservationId(UUID reservationId);

    boolean existsByReservationId(UUID reservationId);

    // Promedio de todos los ratings
    @Query("""
        SELECT AVG((r.serviceCompliance + r.resourceCondition + r.staffKindness) / 3)
        FROM Rating r
    """)
    Double getGlobalAverageScore();


    // Promedio de un rating particular (por reserva)
    @Query("""
        SELECT (r.serviceCompliance + r.resourceCondition + r.staffKindness) / 3
        FROM Rating r
        WHERE r.reservationId = :reservationId
    """)
    Double getAverageByReservation(@Param("reservationId") UUID reservationId);

}