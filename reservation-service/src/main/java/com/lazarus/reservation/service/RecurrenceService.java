package com.lazarus.reservation.service;

import com.lazarus.reservation.dto.recurrence.RecurrenceRequestDTO;
import com.lazarus.reservation.dto.recurrence.RecurrenceResponseDTO;

import java.util.List;
import java.util.UUID;

public interface RecurrenceService {

    List<RecurrenceResponseDTO> findAll();

    RecurrenceResponseDTO findById(UUID id);

    List<RecurrenceResponseDTO> findByUserId(String userId);

    RecurrenceResponseDTO create(RecurrenceRequestDTO dto);

    void delete(UUID id);
}
