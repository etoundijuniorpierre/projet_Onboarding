package com.logisticsCompany.mapper;

import com.logisticsCompany.dto.PackageResponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.dto.microServiceDto.LocationReponseDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.entities.enums.Status;
import com.logisticsCompany.feign.LocationClient;
import com.logisticsCompany.repository.PackageRepository;
import org.bouncycastle.util.Pack;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PackageMapper {

    @Autowired
    private LocationClient locationClient;

     @Autowired
     private PackageRepository packageRepository;

    @Mapping(target = "locationId", source = "location")
    public abstract PackageEntity toEntity(PackageRequestDto packageRequestDto);

    @Mapping(target = "id", source = "packageEntity.id")
    @Mapping(target = "location", source = "locationId", qualifiedByName = "packageWithLocation")
    public abstract PackageResponseDto toDto(PackageEntity packageEntity);

    @Mapping(target = "location", source = "locationId", qualifiedByName = "packageWithLocation")
    public abstract List<PackageResponseDto> toDtoList(List<PackageEntity> packageEntities);

    public abstract void updateEntityFromDto(PackageRequestDto packageRequestDto, @MappingTarget PackageEntity packageEntity);

    @Named("packageWithLocation")
    public LocationReponseDto packageWithLocation(Long id, @MappingTarget PackageEntity packageEntity) {
        ResponseEntity<LocationReponseDto> response = locationClient.getLocationById(String.valueOf(id));
        LocationReponseDto reponseDto = response.getBody();

        if (reponseDto.isCheckpointAvailable()) {
            packageEntity.setStatus(Status.IN_TRANSIT);
        }

        return reponseDto;
    }

}
