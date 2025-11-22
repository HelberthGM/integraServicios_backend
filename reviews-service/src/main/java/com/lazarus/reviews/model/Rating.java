package com.lazarus.reviews.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "rating")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Rating {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "reservation_id", nullable = false, unique = true)
    private UUID reservationId;

    @Column(name = "service_compliance", nullable = false, precision = 2, scale = 1)
    private BigDecimal serviceCompliance;

    @Column(name = "resource_condition", nullable = false, precision = 2, scale = 1)
    private BigDecimal resourceCondition;

    @Column(name = "staff_kindness", nullable = false, precision = 2, scale = 1)
    private BigDecimal staffKindness;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;
}
