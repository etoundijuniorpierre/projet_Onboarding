package com.logisticsCompany.controller;
import com.logisticsCompany.dto.PackageReponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.mapper.PackageMapper;
import com.logisticsCompany.service.ServicePackageEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/compagny")
public class ControllerPackageEntity {
    private final ServicePackageEntity servicePackageEntity;
    private final PackageMapper packageMapper;

    public ControllerPackageEntity(ServicePackageEntity servicePackageEntity, PackageMapper packageMapper) {
        this.servicePackageEntity = servicePackageEntity;
        this.packageMapper = packageMapper;
    }


    @PostMapping("/create")
    public ResponseEntity<PackageReponseDto> create(@Valid @RequestBody PackageRequestDto packageRequestDto ) {
        PackageReponseDto createdPackageEntity = servicePackageEntity.createPackage(packageRequestDto);
        return ResponseEntity.ok(createdPackageEntity);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<PackageReponseDto>> getAll() {
        return ResponseEntity.ok(servicePackageEntity.getAllPackages());
    }


    @GetMapping("/{id}")
    public ResponseEntity<PackageReponseDto> getById(@PathVariable Long id) {
        PackageReponseDto packageEntityRecup = servicePackageEntity.getPackageById(id);
        return ResponseEntity.ok(packageEntityRecup);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PackageReponseDto> update(@PathVariable Long id, @RequestBody PackageRequestDto packageRequestDto) {
        PackageReponseDto updatedPackageEntity = servicePackageEntity.updatePackage(id, packageRequestDto);
        return ResponseEntity.ok(updatedPackageEntity);
    }

}
