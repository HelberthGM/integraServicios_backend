package com.lazarus.resources.dto.type;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceTypeRequestDTO{
        UUID id;
        String unitCode;
        String code;
        String name;
        String description;
        Integer minGranularityMinutes;
} 

