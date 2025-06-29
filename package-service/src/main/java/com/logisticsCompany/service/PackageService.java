package com.logisticsCompany.service;


import com.logisticsCompany.dto.microServiceDto.LocationReponseDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.feign.LocationClient;
import com.logisticsCompany.repository.PackageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class PackageService {


    private final PackageRepository packageRepository;


    private final LocationClient locationClient;


    public PackageService(PackageRepository packageRepository, LocationClient locationClient) {
        this.packageRepository = packageRepository;
        this.locationClient = locationClient;
    }


    // Récupère la localisation d'un colis
    public LocationReponseDto getLocationByPackage(PackageEntity packageEntity) {
        ResponseEntity<LocationReponseDto> response = locationClient.getLocationById(packageEntity.getLocationId().toString());
        return response.getBody();
    }


    //create packageEntity
    public PackageEntity createPackage(PackageEntity packageEntity) {
        return packageRepository.save(packageEntity);
    }

    //getAll
    public List<PackageEntity> getAllPackages() {
        return packageRepository.findAll();
    }


    //getByID
    public PackageEntity getPackageById(Long id) {
        return packageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found with ID: " + id));
    }


    //update
    public PackageEntity updatePackage(Long id, PackageEntity packageEntity) {
        PackageEntity packageEntityExist = packageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
        return packageRepository.save(packageEntityExist);
    }


    //delete
    public void deletePackage(Long id) {
        PackageEntity packageEntityExist = packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found with ID: " + id));
        packageRepository.delete(packageEntityExist);
    }

}
