package com.logisticsCompany.controller;
import com.logisticsCompany.dto.PackageResponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.dto.microServiceDto.LocationReponseDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.mapper.PackageMapper;
import com.logisticsCompany.service.PackageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/package")
public class PackageController {
    private final PackageService packageService;
    private final PackageMapper packageMapper;

    public PackageController(PackageService packageService, PackageMapper packageMapper) {
        this.packageService = packageService;
        this.packageMapper = packageMapper;
    }



    @PostMapping
    public ResponseEntity<PackageResponseDto> create(@Valid @RequestBody PackageRequestDto packageRequestDto ) {
        PackageEntity packageEntity = packageMapper.toEntity(packageRequestDto);
        PackageEntity createPackage = packageService.createPackage(packageEntity);
        PackageResponseDto response = packageMapper.toDto(createPackage);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/all")
    public ResponseEntity<List<PackageResponseDto>> getAll() {
        List<PackageEntity> packageEntities = packageService.getAllPackages();
        return ResponseEntity.ok(packageMapper.toDtoList(packageEntities));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PackageResponseDto> getById(@PathVariable Long id) {
        PackageEntity packageEntity = packageService.getPackageById(id);
        PackageResponseDto packageResponseDto = packageMapper.toDto(packageEntity);
        return ResponseEntity.ok(packageResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageResponseDto> update(@PathVariable Long id, @RequestBody PackageRequestDto packageRequestDto) {
         PackageEntity ExistpackageEntity =  packageService.getPackageById(id);
         packageMapper.updateEntityFromDto(packageRequestDto, ExistpackageEntity);
         ExistpackageEntity.setLocationId(packageRequestDto.getLocation());
         PackageEntity updatedPackageEntity = packageService.updatePackage(id, ExistpackageEntity);
        PackageResponseDto responseDto = packageMapper.toDto(updatedPackageEntity);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        packageService.deletePackage(id);
        return ResponseEntity.ok("Deleted package");
    }

}
