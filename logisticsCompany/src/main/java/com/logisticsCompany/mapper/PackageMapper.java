package com.logisticsCompany.mapper;

import com.logisticsCompany.dto.PackageReponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PackageMapper {

    //dto to entity
    @Mapping(target = "id",ignore = true)
    PackageEntity packageRequestDtoToPackageEntity(PackageRequestDto packageRequestDto);

    //entity to dto
    @Mapping(target = "id", ignore = false)
    PackageReponseDto packageEntityToPackageReponseDto(PackageEntity packageEntity);
}
