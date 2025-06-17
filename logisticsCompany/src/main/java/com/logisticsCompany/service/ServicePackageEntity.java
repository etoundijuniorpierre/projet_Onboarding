package com.logisticsCompany.service;

import com.logisticsCompany.dto.PackageReponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.mapper.PackageMapper;
import com.logisticsCompany.repository.PackageEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public PackageEntity createPackage(PackageRequestDto packageRequestDto) {
        PackageEntity packageEntity = packageMapper.toEntity(packageRequestDto);
        return packageEntityRepository.save(packageEntity);
    }

    //getAll
    public Collection<PackageReponseDto> getAllPackages() {
        return packageEntityRepository.findAll()
                .stream()
                .map(packageMapper::toDto)
                .collect(Collectors.toList());
    }

}
