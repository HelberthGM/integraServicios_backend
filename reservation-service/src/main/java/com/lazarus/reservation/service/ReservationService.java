package com.lazarus.reservation.service;

import com.lazarus.reservation.dto.reservation.ReservationRequestDTO;
import com.lazarus.reservation.dto.reservation.ReservationResponseDTO;
import com.lazarus.reservation.dto.reservation.ReservationUpdateDTO;
import com.lazarus.reservation.model.ReservationStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationService {
    List<ReservationResponseDTO> findAll();
    ReservationResponseDTO findById(UUID id);
    List<ReservationResponseDTO> findByUserId(String userId);
    List<ReservationResponseDTO> findByResourceId(UUID resourceId);
    List<ReservationResponseDTO> findByStatus(ReservationStatus status);
    List<ReservationResponseDTO> findByResourceIdAndDate(
        UUID resourceId,
        LocalDate date
    );
    ReservationResponseDTO create(ReservationRequestDTO dto);
    ReservationResponseDTO update(UUID id, ReservationUpdateDTO dto);
    void delete(UUID id);
    ReservationResponseDTO cancelReservation(UUID id);
    ReservationResponseDTO confirmReservation(UUID id);
}
