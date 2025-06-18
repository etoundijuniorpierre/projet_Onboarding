package com.logisticsCompany.dto;

import com.logisticsCompany.entities.Enum.Status;
import lombok.Data;

@Data
public class PackageRequestDto {

    String description;
    Integer weight;
    public boolean fragile;
    public Status status;
}
