package com.lazarus.reservation.service.impl;

import com.lazarus.reservation.dto.reservation.ReservationRequestDTO;
import com.lazarus.reservation.dto.reservation.ReservationResponseDTO;
import com.lazarus.reservation.dto.reservation.ReservationUpdateDTO;
import com.lazarus.reservation.exception.ResourceNotFoundException;
import com.lazarus.reservation.mapper.ReservationMapper;
import com.lazarus.reservation.model.Reservation;
import com.lazarus.reservation.model.ReservationStatus;
import com.lazarus.reservation.repository.ReservationRepository;
import com.lazarus.reservation.service.ReservationService;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public List<ReservationResponseDTO> findAll() {
        return reservationRepository
            .findAll()
            .stream()
            .map(reservationMapper::toDTO)
            .toList();
    }

    @Override
    public ReservationResponseDTO findById(UUID id) {
        Reservation reservation = reservationRepository
            .findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Reservation not found with id: " + id
                )
            );
        return reservationMapper.toDTO(reservation);
    }

    @Override
    public List<ReservationResponseDTO> findByUserId(String userId) {
        return reservationRepository
            .findByUserId(userId)
            .stream()
            .map(reservationMapper::toDTO)
            .toList();
    }

    @Override
    public List<ReservationResponseDTO> findByResourceId(UUID resourceId) {
        return reservationRepository
            .findByResourceId(resourceId)
            .stream()
            .map(reservationMapper::toDTO)
            .toList();
    }

    @Override
    public List<ReservationResponseDTO> findByStatus(ReservationStatus status) {
        return reservationRepository
            .findByStatus(status)
            .stream()
            .map(reservationMapper::toDTO)
            .toList();
    }

    @Override
    public List<ReservationResponseDTO> findByResourceIdAndDate(
        UUID resourceId,
        LocalDate date
    ) {
        return reservationRepository
            .findByResourceIdAndDateLocal(resourceId, date)
            .stream()
            .map(reservationMapper::toDTO)
            .toList();
    }

    @Override
    @Transactional
    public ReservationResponseDTO create(ReservationRequestDTO dto) {
        Reservation reservation = reservationMapper.toEntity(dto);
        reservation.setCreatedAt(OffsetDateTime.now());
        Reservation savedReservation = reservationRepository.save(reservation);

        log.info("Reservation created: {}", savedReservation.getId());

        return reservationMapper.toDTO(savedReservation);
    }

    @Override
    @Transactional
    public ReservationResponseDTO update(UUID id, ReservationUpdateDTO dto) {
        Reservation reservation = reservationRepository
            .findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Reservation not found with id: " + id
                )
            );

        reservationMapper.updateEntityFromDTO(dto, reservation);
        Reservation updatedReservation = reservationRepository.save(
            reservation
        );

        log.info("Reservation updated: {}", updatedReservation.getId());

        return reservationMapper.toDTO(updatedReservation);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                "Reservation not found with id: " + id
            );
        }

        reservationRepository.deleteById(id);
        log.info("Reservation deleted with id: {}", id);
    }

    @Override
    @Transactional
    public ReservationResponseDTO cancelReservation(UUID id) {
        Reservation reservation = reservationRepository
            .findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Reservation not found with id: " + id
                )
            );

        reservation.setStatus(ReservationStatus.CANCELADA);
        Reservation canceledReservation = reservationRepository.save(
            reservation
        );

        log.info("Reservation canceled: {}", canceledReservation.getId());

        return reservationMapper.toDTO(canceledReservation);
    }

    @Override
    @Transactional
    public ReservationResponseDTO confirmReservation(UUID id) {
        Reservation reservation = reservationRepository
            .findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Reservation not found with id: " + id
                )
            );

        reservation.setStatus(ReservationStatus.CONFIRMADA);
        Reservation confirmedReservation = reservationRepository.save(
            reservation
        );

        log.info("Reservation confirmed: {}", confirmedReservation.getId());

        return reservationMapper.toDTO(confirmedReservation);
    }
}
