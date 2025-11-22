package com.lazarus.resources.mapper;

import com.lazarus.resources.dto.common.IdOnlyDTO;
import com.lazarus.resources.dto.location.ResourceLocationResponseDTO;
import com.lazarus.resources.dto.resource.ResourceRequestDTO;
import com.lazarus.resources.dto.resource.ResourceResponseDTO;
import com.lazarus.resources.dto.type.ResourceTypeResponseDTO;
import com.lazarus.resources.model.Resource;
import com.lazarus.resources.model.ResourceLocation;
import com.lazarus.resources.model.ResourceType;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ResourceMapper {

    /* =============================
       ENTITY → DTO
    ============================= */

    @Mapping(target = "location", qualifiedByName = "locationToDTO")
    @Mapping(target = "type", qualifiedByName = "typeToDTO")
    ResourceResponseDTO toDTO(Resource entity);

    /* =============================
       DTO → ENTITY
    ============================= */

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "location", source = "location", qualifiedByName = "idToLocation")
    @Mapping(target = "type", source = "type", qualifiedByName = "idToType")
    Resource toEntity(ResourceRequestDTO dto);

    /* =============================
       UPDATE (PATCH/PUT)
    ============================= */
    @Mapping(target = "location", source = "location", qualifiedByName = "idToLocation")
    @Mapping(target = "type", source = "type", qualifiedByName = "idToType")
    void updateEntityFromDTO(ResourceRequestDTO dto, @MappingTarget Resource entity);


    /* =============================
       HELPERS
       (id → entity)
    ============================= */

    @Named("idToLocation")
    default ResourceLocation idToLocation(IdOnlyDTO dto) {
        if (dto == null || dto.id() == null) return null;
        ResourceLocation loc = new ResourceLocation();
        loc.setId(dto.id());
        return loc;
    }

    @Named("idToType")
    default ResourceType idToType(IdOnlyDTO dto) {
        if (dto == null || dto.id() == null) return null;
        ResourceType type = new ResourceType();
        type.setId(dto.id());
        return type;
    }

    /* =============================
       HELPERS
       (entity → DTO)
    ============================= */

    @Named("locationToDTO")
    default ResourceLocationResponseDTO locationToDTO(ResourceLocation loc) {
        if (loc == null) return null;
        return new ResourceLocationResponseDTO(
            loc.getId(),
            loc.getName(),
            loc.getCampus(),
            loc.getBuilding(),
            loc.getFloor(),
            loc.getRoom(),
            loc.getDescription()
        );
    }

    @Named("typeToDTO")
    default ResourceTypeResponseDTO typeToDTO(ResourceType type) {
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
}