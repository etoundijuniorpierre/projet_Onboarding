package com.logisticsCompany.dto;

import com.logisticsCompany.dto.microServiceDto.LocationReponseDto;
import com.logisticsCompany.entities.enums.Status;
import lombok.Data;

@Data
public class PackageResponseDto {
    private Long id;
    private String description;
    private Integer weight;
    private boolean fragile;
    private Status status;
    private LocationReponseDto location;
}
