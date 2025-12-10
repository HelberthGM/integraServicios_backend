package com.lazarus.reservation.mapper;

import com.lazarus.reservation.dto.recurrence.RecurrenceRequestDTO;
import com.lazarus.reservation.dto.recurrence.RecurrenceResponseDTO;
import com.lazarus.reservation.model.ReservationRecurrence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecurrenceMapper {

    RecurrenceResponseDTO toDTO(ReservationRecurrence recurrence);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ReservationRecurrence toEntity(RecurrenceRequestDTO dto);
}
