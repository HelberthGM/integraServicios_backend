package com.lazarus.resources.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "resource")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Resource {

    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String code;
    private String name;

    @Column(name = "attributes", columnDefinition = "jsonb")
    private String attributesJson; // JSON en texto; se puede parsear en controller/servicio

    private String photoUrl;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private ResourceLocation location;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ResourceType type;
}