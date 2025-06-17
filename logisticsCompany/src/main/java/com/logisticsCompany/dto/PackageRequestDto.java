package com.logisticsCompany.dto;

import com.logisticsCompany.entities.enums.Status;
import lombok.Data;

@Data
public class PackageRequestDto {

    String description;
    Integer weight;
    public Boolean fragile;
    public Status status;
}
