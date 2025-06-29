package com.logisticsCompany.dto;


import com.logisticsCompany.entities.enums.Status;
import lombok.Data;

@Data
public class PackageResponseDto {
    private Long id;
    private String description;
    private Integer weight;
    private boolean fragile;
    private Status status;

}
