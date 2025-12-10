package com.lazarus.reservation.mapper;

import com.lazarus.reservation.dto.CreateReservationRecurrenceDto;
import com.lazarus.reservation.dto.ReservationRecurrenceDto;
import com.lazarus.reservation.model.ReservationRecurrence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationRecurrenceMapper {

    ReservationRecurrenceDto toDto(ReservationRecurrence recurrence);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ReservationRecurrence toEntity(CreateReservationRecurrenceDto createDto);
}
