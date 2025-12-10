package com.lazarus.loan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;


@Entity
@Table(name = "loan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "reservation_id", nullable = false, unique = true)
    private UUID reservationId;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Column(name = "planned_start", nullable = false)
    private ZonedDateTime plannedStart;

    @Column(name = "delivered_at", nullable = false)
    private ZonedDateTime deliveredAt;

    @Column(name = "service_failure", nullable = false)
    private Boolean serviceFailure;

    private String notes;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = ZonedDateTime.now();
        this.serviceFailure = 
            Math.abs(deliveredAt.toEpochSecond() - plannedStart.toEpochSecond()) > 300;
    }

}
