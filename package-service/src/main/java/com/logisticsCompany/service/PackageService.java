package com.logisticsCompany.service;

import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.mapper.PackageMapper;
import com.logisticsCompany.repository.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//import static com.logisticsCompany.entities.enums.Status.DELIVERED;

@Service
public class PackageService {

    private final PackageRepository packageRepository;
    private final PackageMapper packageMapper;

    public PackageService(PackageRepository packageRepository, PackageMapper packageMapper) {
        this.packageRepository = packageRepository;
        this.packageMapper = packageMapper;
    }


    //create packageEntity
    public PackageEntity createPackage(PackageRequestDto packageRequestDto) {
        PackageEntity packageEntity = packageMapper.toEntity(packageRequestDto);
        return packageRepository.save(packageEntity);
    }

    //getAll
    public List<PackageEntity> getAllPackages() {
        return packageRepository.findAll();
    }


    //getByID
    public PackageEntity getPackageById(Long id) {
        return packageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
    }


    //update
    public PackageEntity updatePackage(Long id, PackageRequestDto packageRequestDto) {
        //search Entity to update byIb
        PackageEntity packageEntityExist = packageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));

        //if package exist update Entity from Requestdto
        packageMapper.updateEntityFromDto(packageRequestDto, packageEntityExist);

        //save modification
        return packageRepository.save(packageEntityExist);
    }


    //delete
    public void deletePackage(Long id) {
        PackageEntity packageEntityExist = packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found with ID: " + id));
        packageRepository.delete(packageEntityExist);
    }

}
