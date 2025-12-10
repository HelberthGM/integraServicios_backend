package com.lazarus.reservation.service.impl;

import com.lazarus.reservation.dto.CreateReservationRecurrenceDto;
import com.lazarus.reservation.dto.ReservationRecurrenceDto;
import com.lazarus.reservation.exception.ResourceNotFoundException;
import com.lazarus.reservation.mapper.ReservationRecurrenceMapper;
import com.lazarus.reservation.model.ReservationRecurrence;
import com.lazarus.reservation.repository.ReservationRecurrenceRepository;
import com.lazarus.reservation.service.ReservationRecurrenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationRecurrenceServiceImpl implements ReservationRecurrenceService {

    private final ReservationRecurrenceRepository recurrenceRepository;
    private final ReservationRecurrenceMapper recurrenceMapper;

    @Override
    public List<ReservationRecurrenceDto> getAllReservationRecurrences() {
        log.debug("Fetching all reservation recurrences");
        return recurrenceRepository.findAll()
                .stream()
                .map(recurrenceMapper::toDto)
                .toList();
    }

    @Override
    public ReservationRecurrenceDto getReservationRecurrenceById(UUID id) {
        log.debug("Fetching reservation recurrence with id: {}", id);
        ReservationRecurrence recurrence = recurrenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation recurrence not found with id: " + id));
        return recurrenceMapper.toDto(recurrence);
    }

    @Override
    public List<ReservationRecurrenceDto> getReservationRecurrencesByUserId(String userId) {
        log.debug("Fetching reservation recurrences for user: {}", userId);
        return recurrenceRepository.findByUserId(userId)
                .stream()
                .map(recurrenceMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ReservationRecurrenceDto createReservationRecurrence(CreateReservationRecurrenceDto createDto) {
        log.debug("Creating new reservation recurrence for user: {}", createDto.getUserId());

        ReservationRecurrence recurrence = recurrenceMapper.toEntity(createDto);
        recurrence.setCreatedAt(OffsetDateTime.now());

        ReservationRecurrence savedRecurrence = recurrenceRepository.save(recurrence);
        log.info("Created reservation recurrence with id: {}", savedRecurrence.getId());

        return recurrenceMapper.toDto(savedRecurrence);
    }

    @Override
    @Transactional
    public void deleteReservationRecurrence(UUID id) {
        log.debug("Deleting reservation recurrence with id: {}", id);

        if (!recurrenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservation recurrence not found with id: " + id);
        }

        recurrenceRepository.deleteById(id);
        log.info("Deleted reservation recurrence with id: {}", id);
    }
}
