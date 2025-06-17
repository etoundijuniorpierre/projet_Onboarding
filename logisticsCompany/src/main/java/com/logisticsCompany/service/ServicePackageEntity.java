package com.logisticsCompany.service;

import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.mapper.PackageMapper;
import com.logisticsCompany.repository.PackageEntityRepository;
import org.springframework.stereotype.Service;

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


}
