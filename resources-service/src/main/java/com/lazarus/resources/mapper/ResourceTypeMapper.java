package com.lazarus.resources.mapper;

import com.lazarus.resources.dto.type.ResourceTypeRequestDTO;
import com.lazarus.resources.dto.type.ResourceTypeResponseDTO;
import com.lazarus.resources.model.ResourceType;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResourceTypeMapper {

    @Mapping(target = "id", ignore = true)
    ResourceType toEntity(ResourceTypeRequestDTO dto);

    ResourceTypeResponseDTO toDTO(ResourceType entity);
}
