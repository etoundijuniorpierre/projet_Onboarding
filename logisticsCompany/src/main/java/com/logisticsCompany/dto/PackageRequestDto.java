package com.logisticsCompany.dto;

import com.logisticsCompany.entities.Enum.Status;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PackageRequestDto {

    String description;
    Integer weight;
    public Boolean fragile;
    public Status status;
}
