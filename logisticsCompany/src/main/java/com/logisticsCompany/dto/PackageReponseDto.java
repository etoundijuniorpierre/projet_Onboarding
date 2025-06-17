package com.logisticsCompany.dto;

import com.logisticsCompany.entities.enums.Status;
import lombok.Data;

@Data
public class PackageReponseDto {
    public int id;
    public String description;
    public Integer weight;
    public Boolean fragile;
    public Status status;
}
