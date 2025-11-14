package com.lazarus.resources.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "resource_location")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ResourceLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String name;
    private String campus;
    private String building;
    private String floor;
    private String room;
    
    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Resource> resources;
}
