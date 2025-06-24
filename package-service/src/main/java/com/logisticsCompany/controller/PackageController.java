package com.logisticsCompany.controller;
import com.logisticsCompany.dto.PackageReponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.mapper.PackageMapper;
import com.logisticsCompany.service.PackageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compagny")
public class PackageController {
    private final PackageService packageService;
    private final PackageMapper packageMapper;

    public PackageController(PackageService packageService, PackageMapper packageMapper) {
        this.packageService = packageService;
        this.packageMapper = packageMapper;
    }


    @PostMapping
    public ResponseEntity<PackageReponseDto> create(@Valid @RequestBody PackageRequestDto packageRequestDto ) {
        PackageEntity packageEntity = packageMapper.toEntity(packageRequestDto);
        PackageEntity createPackage = packageService.createPackage(packageEntity);
        PackageReponseDto createdPackageEntity = packageMapper.toDto(createPackage);
        return ResponseEntity.ok(createdPackageEntity);
    }


    @GetMapping("/all")
    public ResponseEntity<List<PackageReponseDto>> getAll() {
        List<PackageEntity> packageEntities = packageService.getAllPackages();
        return ResponseEntity.ok(packageMapper.toDtoList(packageEntities));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PackageReponseDto> getById(@PathVariable Long id) {
        PackageEntity packageEntity = packageService.getPackageById(id);
        PackageReponseDto packageReponseDto = packageMapper.toDto(packageEntity);
        return ResponseEntity.ok(packageReponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageReponseDto> update(@PathVariable Long id, @RequestBody PackageRequestDto packageRequestDto) {
         PackageEntity ExistpackageEntity =  packageService.getPackageById(id);
         packageMapper.updateEntityFromDto(packageRequestDto, ExistpackageEntity);
         PackageEntity updatedPackageEntity = packageService.updatePackage(id, ExistpackageEntity);
         return ResponseEntity.ok(packageMapper.toDto(updatedPackageEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        packageService.deletePackage(id);
        return ResponseEntity.ok("Deleted package");
    }

}
