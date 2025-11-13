package com.lazarus.resources.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "resource_type")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ResourceType {

    @Id
    @GeneratedValue
    private UUID id;

    private String unitCode;
    private String code;
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    private Integer minGranularityMinutes;

    @OneToMany(mappedBy = "type")
    private List<Resource> resources;

    @OneToMany(mappedBy = "resourceType")
    private List<ResourceTypeAvailability> availability;
}
