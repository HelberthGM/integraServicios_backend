package com.lazarus.resources.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.util.Map;

@Entity
@Table(name = "resource")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Resource {

    @Id
    @GeneratedValue
    private UUID id;

    private String code;
    private String name;

    @Column(columnDefinition = "jsonb")
    private Map<String, Object> attributes;

    private String photoUrl;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private ResourceLocation location;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ResourceType type;
}