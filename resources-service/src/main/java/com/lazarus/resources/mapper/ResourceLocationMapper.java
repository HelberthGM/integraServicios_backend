package com.lazarus.resources.mapper;

import com.lazarus.resources.dto.common.IdOnlyDTO;
import com.lazarus.resources.dto.location.*;
import com.lazarus.resources.model.ResourceLocation;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ResourceLocationMapper {

    @Mapping(target = "id", ignore = true) // ID lo asigna JPA
    ResourceLocation toEntity(ResourceLocationRequestDTO dto);

    ResourceLocationResponseDTO toDTO(ResourceLocation location);

    @Named("idToLocation")
    default ResourceLocation idToLocation(IdOnlyDTO dto) {
        if (dto == null) return null;
        ResourceLocation loc = new ResourceLocation();
        loc.setId(dto.id());
        return loc;
    }
    
}
