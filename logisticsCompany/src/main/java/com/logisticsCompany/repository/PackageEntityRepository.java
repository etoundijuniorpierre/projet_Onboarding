package com.logisticsCompany.repository;

import com.logisticsCompany.dto.PackageReponseDto;
import com.logisticsCompany.entities.PackageEntity;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageEntityRepository extends JpaRepository<PackageEntity, Integer> {

}
