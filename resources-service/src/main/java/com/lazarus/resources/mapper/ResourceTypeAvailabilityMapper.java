package com.lazarus.resources.mapper;

import com.lazarus.resources.model.ResourceType;
import com.lazarus.resources.model.ResourceTypeAvailability;
import com.lazarus.resources.dto.type.*;

import java.util.UUID;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ResourceTypeAvailabilityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "resourceType",
             source = "resourceType.id",
             qualifiedByName = "mapResourceTypeById")
    ResourceTypeAvailability toEntity(ResourceTypeAvailabilityRequestDTO dto);

    @Mapping(target = "resourceType", qualifiedByName = "mapTypeToDTO")
    ResourceTypeAvailabilityResponseDTO toDTO(ResourceTypeAvailability entity);

    @Named("mapTypeToDTO")
    default ResourceTypeResponseDTO mapTypeToDTO(ResourceType type) {
        if (type == null) return null;
        return new ResourceTypeResponseDTO(
                type.getId(),
                type.getUnitCode(),
                type.getCode(),
                type.getName(),
                type.getDescription(),
                type.getMinGranularityMinutes()
        );
    }

    @Named("mapResourceTypeById")
    default ResourceType mapResourceTypeById(UUID id) {
        if (id == null) return null;
        ResourceType rt = new ResourceType();
        rt.setId(id);
        return rt;
    }
}
