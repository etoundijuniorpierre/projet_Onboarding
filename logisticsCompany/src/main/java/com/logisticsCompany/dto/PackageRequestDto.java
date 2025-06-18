package com.logisticsCompany.dto;

import com.logisticsCompany.entities.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class PackageRequestDto {
    @NotBlank(message = "description cannot be null")
    String description;

    @NotNull(message = "weight cannot be null")
    Integer weight;

    public Boolean fragile;

    @NotNull(message = "status cannot be null")
    public Status status;
}
