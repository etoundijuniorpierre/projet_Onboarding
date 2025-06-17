package com.logisticsCompany.controller;
import com.logisticsCompany.dto.PackageReponseDto;
import com.logisticsCompany.dto.PackageRequestDto;
import com.logisticsCompany.entities.PackageEntity;
import com.logisticsCompany.mapper.PackageMapper;
import com.logisticsCompany.service.ServicePackageEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
    public ResponseEntity<PackageReponseDto> create(@RequestBody PackageRequestDto packageRequestDto ) {
        //create entity
        PackageEntity createdPackageEntity = servicePackageEntity.createPackage(packageRequestDto);
        //mapping entity to ResponseDto
        PackageReponseDto packageReponseDto = packageMapper.packageEntityToPackageReponseDto(createdPackageEntity);
        return ResponseEntity.ok(packageReponseDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Collection<PackageReponseDto>> getAll() {
        return ResponseEntity.ok(servicePackageEntity.getAllPackages());
    }

}
