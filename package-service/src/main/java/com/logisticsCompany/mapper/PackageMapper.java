package com.logisticsCompany.mapper;

import com.logisticsCompany.dto.PackageResponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.dto.microServiceDto.LocationReponseDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.feign.LocationClient;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PackageMapper {

    @Autowired
    private LocationClient locationClient;

    @Mapping(target = "locationId", source = "location")
    public abstract PackageEntity toEntity(PackageRequestDto packageRequestDto);

    @Mapping(target = "id", source = "packageEntity.id")
    @Mapping(target = "location", source = "locationId", qualifiedByName = "packageWithLocation")
    public abstract PackageResponseDto toDto(PackageEntity packageEntity);

    @Mapping(target = "location", source = "locationId", qualifiedByName = "packageWithLocation")
    public abstract List<PackageResponseDto> toDtoList(List<PackageEntity> packageEntities);

    public abstract void updateEntityFromDto(PackageRequestDto packageRequestDto, @MappingTarget PackageEntity packageEntity);

    @Named("packageWithLocation")
    LocationReponseDto packageWithLocation(Long id) {
        return locationClient.getLocationById(String.valueOf(id)).getBody();
    }

}
