package com.logisticsCompany.feign;

import com.logisticsCompany.dto.microServiceDto.LocationReponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "location-service", url = "http://localhost:8081/api/location")
public interface LocationClient {

    @GetMapping("/{id}")
    ResponseEntity<LocationReponseDto> getLocationById(@PathVariable String id);
}
