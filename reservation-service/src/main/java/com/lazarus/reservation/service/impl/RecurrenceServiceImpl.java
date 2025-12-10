package com.lazarus.reservation.service.impl;

import com.lazarus.reservation.dto.recurrence.RecurrenceRequestDTO;
import com.lazarus.reservation.dto.recurrence.RecurrenceResponseDTO;
import com.lazarus.reservation.exception.ResourceNotFoundException;
import com.lazarus.reservation.mapper.RecurrenceMapper;
import com.lazarus.reservation.model.ReservationRecurrence;
import com.lazarus.reservation.repository.ReservationRecurrenceRepository;
import com.lazarus.reservation.service.RecurrenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecurrenceServiceImpl implements RecurrenceService {

    private final ReservationRecurrenceRepository recurrenceRepository;
    private final RecurrenceMapper recurrenceMapper;

    @Override
    public List<RecurrenceResponseDTO> findAll() {
        return recurrenceRepository.findAll()
                .stream()
                .map(recurrenceMapper::toDTO)
                .toList();
    }

    @Override
    public RecurrenceResponseDTO findById(UUID id) {
        ReservationRecurrence recurrence = recurrenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurrence not found with id: " + id));
        return recurrenceMapper.toDTO(recurrence);
    }

    @Override
    public List<RecurrenceResponseDTO> findByUserId(String userId) {
        return recurrenceRepository.findByUserId(userId)
                .stream()
                .map(recurrenceMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public RecurrenceResponseDTO create(RecurrenceRequestDTO dto) {
        ReservationRecurrence recurrence = recurrenceMapper.toEntity(dto);
        ReservationRecurrence savedRecurrence = recurrenceRepository.save(recurrence);

        log.info("Recurrence created: {}", savedRecurrence.getId());

        return recurrenceMapper.toDTO(savedRecurrence);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!recurrenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurrence not found with id: " + id);
        }

        recurrenceRepository.deleteById(id);
        log.info("Recurrence deleted with id: {}", id);
    }
}
