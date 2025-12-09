package com.lazarus.auth.mapper;

import com.lazarus.auth.dto.user.UserRequestDTO;
import com.lazarus.auth.dto.user.UserResponseDTO;
import com.lazarus.auth.dto.user.UserUpdateDTO;
import com.lazarus.auth.model.UserAccount;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Entity → DTO
    UserResponseDTO toDTO(UserAccount userAccount);

    // DTO → Entity (for creation, ignore generated fields)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserAccount toEntity(UserRequestDTO dto);

    // Update entity from DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(
        UserUpdateDTO dto,
        @MappingTarget UserAccount userAccount
    );
}
