package com.lazarus.resources.dto.type;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceTypeResponseDTO {

    private UUID id;
    private String unitCode;
    private String code;
    private String name;
    private String description;
    private Integer minGranularityMinutes;
}
