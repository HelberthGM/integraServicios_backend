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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String unitCode;
    private String code;
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    private Integer minGranularityMinutes;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Resource> resources;

    @OneToMany(mappedBy = "resourceType", cascade = CascadeType.ALL)
    private List<ResourceTypeAvailability> availability;
}
