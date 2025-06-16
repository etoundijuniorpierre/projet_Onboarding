package com.logisticsCompany.service;

import com.logisticsCompany.dto.PackageReponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.mapper.PackageMapper;
import com.logisticsCompany.repository.PackageEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.logisticsCompany.entities.Enum.Status.DELIVERED;

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
        PackageEntity packageEntity = packageMapper.packageRequestDtoToPackageEntity(packageRequestDto);
        return packageEntityRepository.save(packageEntity);
    }


}
