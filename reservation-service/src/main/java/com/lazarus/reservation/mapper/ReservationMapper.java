package com.lazarus.reservation.mapper;

import com.lazarus.reservation.dto.reservation.ReservationRequestDTO;
import com.lazarus.reservation.dto.reservation.ReservationResponseDTO;
import com.lazarus.reservation.dto.reservation.ReservationUpdateDTO;
import com.lazarus.reservation.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationResponseDTO toDTO(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "tsRange", ignore = true)
    Reservation toEntity(ReservationRequestDTO requestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "tsRange", ignore = true)
    void updateEntityFromDTO(
        ReservationUpdateDTO updateDTO,
        @MappingTarget Reservation reservation
    );
}
