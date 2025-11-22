package com.lazarus.reviews.dto;

import jakarta.validation.constraints.*;

public class UpdateRatingRequest {

    @NotNull(message = "serviceCompliance is required")
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    @Digits(integer = 1, fraction = 1)
    private Double serviceCompliance;

    @NotNull(message = "resourceCondition is required")
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    @Digits(integer = 1, fraction = 1)
    private Double resourceCondition;

    @NotNull(message = "staffKindness is required")
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    @Digits(integer = 1, fraction = 1)
    private Double staffKindness;

    public Double getServiceCompliance() {
        return serviceCompliance;
    }

    public void setServiceCompliance(Double serviceCompliance) {
        this.serviceCompliance = serviceCompliance;
    }

    public Double getResourceCondition() {
        return resourceCondition;
    }

    public void setResourceCondition(Double resourceCondition) {
        this.resourceCondition = resourceCondition;
    }

    public Double getStaffKindness() {
        return staffKindness;
    }

    public void setStaffKindness(Double staffKindness) {
        this.staffKindness = staffKindness;
    }
}

