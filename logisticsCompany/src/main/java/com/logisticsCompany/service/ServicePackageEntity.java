package com.logisticsCompany.service;

import com.logisticsCompany.dto.PackageReponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.entities.enums.Status;
import com.logisticsCompany.mapper.PackageMapper;
import com.logisticsCompany.repository.PackageEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//import static com.logisticsCompany.entities.enums.Status.DELIVERED;

@Service
public class ServicePackageEntity {

    private final PackageEntityRepository packageEntityRepository;
    private final PackageMapper packageMapper;

    public ServicePackageEntity(PackageEntityRepository packageEntityRepository, PackageMapper packageMapper) {
        this.packageEntityRepository = packageEntityRepository;
        this.packageMapper = packageMapper;
    }

    //create packageEntity
    public PackageReponseDto createPackage(PackageRequestDto packageRequestDto) {
        PackageEntity packageEntity = packageMapper.toEntity(packageRequestDto);
        PackageEntity savedPackageEntity = packageEntityRepository.save(packageEntity);
        return packageMapper.toDto(savedPackageEntity);
    }

    //getAll
    public List<PackageReponseDto> getAllPackages() {
        List<PackageEntity> packageEntities = packageEntityRepository.findAll();
        return packageMapper.toDtoList(packageEntities);
    }

    //getByID
    public PackageReponseDto getPackageById(Long id) {
        PackageEntity packageEntityExist = packageEntityRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
        return packageMapper.toDto(packageEntityExist);
    }

    //update
    public PackageReponseDto updatePackage(Long id, PackageRequestDto packageRequestDto) {
        //search Entity to update byIb
        PackageEntity packageEntityExist = packageEntityRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));

        //if package exist update Entity from Requestdto
        packageMapper.updateEntityFromDto(packageRequestDto, packageEntityExist);

        //save modification
        PackageEntity savedPackageEntity = packageEntityRepository.save(packageEntityExist);

        //return a Entity to ReponseDto
        return  packageMapper.toDto(savedPackageEntity);
    }

}
