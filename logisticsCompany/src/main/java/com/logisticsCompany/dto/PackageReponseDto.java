package com.logisticsCompany.dto;

import com.logisticsCompany.entities.enums.Status;
import lombok.Data;

@Data
public class PackageReponseDto {
    public Long id;
    public String description;
    public Integer weight;
    public boolean fragile;
    public Status status;
}
