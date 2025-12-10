package com.lazarus.reservation.repository;

import com.lazarus.reservation.model.Reservation;
import com.lazarus.reservation.model.ReservationStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository
    extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByUserId(String userId);
    List<Reservation> findByResourceId(UUID resourceId);
    List<Reservation> findByStatus(ReservationStatus status);
    List<Reservation> findByResourceIdAndDateLocal(
        UUID resourceId,
        LocalDate dateLocal
    );
}
