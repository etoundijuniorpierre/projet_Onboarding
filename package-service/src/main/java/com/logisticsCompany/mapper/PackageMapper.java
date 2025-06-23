package com.logisticsCompany.mapper;

import com.logisticsCompany.dto.PackageReponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        // Ignore les champs null du DTO lors du mappage
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PackageMapper {

    //dto to entity
    PackageEntity toEntity(PackageRequestDto packageRequestDto);

    //entity to dto
    @Mapping(target = "id")
    PackageReponseDto toDto(PackageEntity packageEntity);

    //list entity to dto
    List<PackageReponseDto> toDtoList(List<PackageEntity> packageEntities);

    void updateEntityFromDto(PackageRequestDto packageRequestDto, @MappingTarget PackageEntity packageEntity);
}
