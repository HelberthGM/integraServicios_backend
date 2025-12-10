package com.lazarus.reservation.repository;

import com.lazarus.reservation.model.ReservationRecurrence;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRecurrenceRepository
    extends JpaRepository<ReservationRecurrence, UUID> {
    List<ReservationRecurrence> findByUserId(String userId);
}
