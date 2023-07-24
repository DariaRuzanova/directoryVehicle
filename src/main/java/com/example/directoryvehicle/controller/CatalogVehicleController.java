package com.example.directoryvehicle.controller;

import com.example.directoryvehicle.DTO.RequestVehicle;
import com.example.directoryvehicle.DTO.ResponseVehicle;
import com.example.directoryvehicle.service.VehicleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@Validated
public class CatalogVehicleController {
    private VehicleService vehicleService;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVehicle> addVehicle(@RequestBody @Valid RequestVehicle requestVehicle){
        return vehicleService.addVehicle(requestVehicle);
    }
}
