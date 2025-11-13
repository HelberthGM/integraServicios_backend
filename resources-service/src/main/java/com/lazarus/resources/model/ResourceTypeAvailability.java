package com.lazarus.resources.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.time.LocalTime;

@Entity
@Table(name = "resource_type_availability")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ResourceTypeAvailability {

    @Id
    @GeneratedValue
    private UUID id;

    private Integer weekday;

    private LocalTime startTime;
    private LocalTime endTime;

    private Boolean isOpen;

    @ManyToOne
    @JoinColumn(name = "resource_type_id")
    private ResourceType resourceType;
}
