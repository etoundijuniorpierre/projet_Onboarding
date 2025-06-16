package com.logisticsCompany.mapper;

import com.logisticsCompany.dto.PackageReponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-16T20:27:48+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class PackageMapperImpl implements PackageMapper {

    @Override
    public PackageEntity packageRequestDtoToPackageEntity(PackageRequestDto packageRequestDto) {
        if ( packageRequestDto == null ) {
            return null;
        }

        PackageEntity packageEntity = new PackageEntity();

        packageEntity.setDescription( packageRequestDto.getDescription() );
        packageEntity.setWeight( packageRequestDto.getWeight() );
        packageEntity.setFragile( packageRequestDto.getFragile() );
        packageEntity.setStatus( packageRequestDto.getStatus() );

        return packageEntity;
    }

    @Override
    public PackageReponseDto packageEntityToPackageReponseDto(PackageEntity packageEntity) {
        if ( packageEntity == null ) {
            return null;
        }

        PackageReponseDto packageReponseDto = new PackageReponseDto();

        packageReponseDto.setId( packageEntity.getId() );
        packageReponseDto.setDescription( packageEntity.getDescription() );
        packageReponseDto.setWeight( packageEntity.getWeight() );
        packageReponseDto.setFragile( packageEntity.getFragile() );
        packageReponseDto.setStatus( packageEntity.getStatus() );

        return packageReponseDto;
    }
}
