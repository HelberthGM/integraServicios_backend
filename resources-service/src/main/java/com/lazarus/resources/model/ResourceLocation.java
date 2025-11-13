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
    @GeneratedValue
    private UUID id;

    private String name;
    private String campus;
    private String building;
    private String floor;
    private String room;
    
    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "location")
    private List<Resource> resources;
}
