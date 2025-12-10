package com.lazarus.reservation.service;

import com.lazarus.reservation.dto.CreateReservationRecurrenceDto;
import com.lazarus.reservation.dto.ReservationRecurrenceDto;
import java.util.List;
import java.util.UUID;

public interface ReservationRecurrenceService {

    List<ReservationRecurrenceDto> getAllReservationRecurrences();

    ReservationRecurrenceDto getReservationRecurrenceById(UUID id);

    List<ReservationRecurrenceDto> getReservationRecurrencesByUserId(String userId);

    ReservationRecurrenceDto createReservationRecurrence(CreateReservationRecurrenceDto createDto);

    void deleteReservationRecurrence(UUID id);
}
