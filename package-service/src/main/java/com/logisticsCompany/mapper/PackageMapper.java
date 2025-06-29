package com.logisticsCompany.mapper;

import com.logisticsCompany.dto.PackageResponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.dto.microServiceDto.LocationReponseDto;
import com.logisticsCompany.entities.PackageEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",

        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PackageMapper {

    @Mapping(target = "locationId", source = "location")
    PackageEntity toEntity(PackageRequestDto packageRequestDto);

    @Mapping(target = "id", source = "packageEntity.id")
    @Mapping(target = "location", source="locationReponseDto")
    PackageResponseDto toDto(PackageEntity packageEntity, LocationReponseDto locationReponseDto);

    List<PackageResponseDto> toDtoList(List<PackageEntity> packageEntities);

    void updateEntityFromDto(PackageRequestDto packageRequestDto, @MappingTarget PackageEntity packageEntity);

}
