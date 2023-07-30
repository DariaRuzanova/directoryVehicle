package com.example.directoryvehicle.controller;

import com.example.directoryvehicle.DTO.RequestVehicle;
import com.example.directoryvehicle.entity.Vehicle;
import com.example.directoryvehicle.service.VehicleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
@Validated
public class CatalogVehicleController {
    private VehicleService vehicleService;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addVehicle(@RequestBody @Valid RequestVehicle requestVehicle) {
        boolean flag = vehicleService.addVehicle(requestVehicle);
        String stateNumber = requestVehicle.getStateNumber();
        if (!flag) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body("Данные транспортного средства с регистрационным номером " + stateNumber + " успешно загружены в базу");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> listVehicle = vehicleService.getAllVehicles();
        return ResponseEntity.ok().body(listVehicle);
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> getVehiclesById(@PathVariable("id") Long id) {
        return Optional
                .ofNullable(vehicleService.getVehicleById(id))
                .map(vehicle -> ResponseEntity.ok().body(vehicle))    //200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); //404 Not found
    }

    @GetMapping(value = "/find/{mark}")
    public ResponseEntity<List<Vehicle>> getVehiclesByMark(@PathVariable("mark") String mark) {
        List<Vehicle> listVehiclesByMark = vehicleService.getVehiclesByMark(mark);
        if (listVehiclesByMark == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listVehiclesByMark);

    }

    @GetMapping(value = "/find/{model}")
    public ResponseEntity<List<Vehicle>> getVehiclesByModel(@PathVariable("model") String model) {
        List<Vehicle> listVehiclesByModel = vehicleService.getVehiclesByModel(model);
        if (listVehiclesByModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listVehiclesByModel);
    }

    @GetMapping(value = "/find/{category}")
    public ResponseEntity<List<Vehicle>> getVehiclesByCategory(@PathVariable("category") char category) {
        List<Vehicle> listVehiclesByCategory = vehicleService.getVehiclesByCategory(category);
        if (listVehiclesByCategory == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listVehiclesByCategory);
    }

    @GetMapping(value = "/find/{state_number}")
    protected ResponseEntity<Vehicle> getVehiclesByNumber(@PathVariable("state_number") String stateNumber) {
        Vehicle VehiclesByStateNumber = vehicleService.getVehiclesByStateNumber(stateNumber);
        if (VehiclesByStateNumber == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(VehiclesByStateNumber);
    }

    @GetMapping(value = "/find/{year_release}")
    public ResponseEntity<List<Vehicle>> getVehiclesByYear(@PathVariable("year_release") int yearRelease) {
        List<Vehicle> listVehiclesByYear = vehicleService.getVehicleByYear(yearRelease);
        if (listVehiclesByYear == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listVehiclesByYear);
    }

    @PutMapping("/update")
    public ResponseEntity<Vehicle> update(@RequestBody @Valid Vehicle vehicle) {
        Vehicle updatingVehicle = vehicleService.update(vehicle);
        return ResponseEntity.ok().body(updatingVehicle);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") Long id) {
        boolean response = vehicleService.deleteVehicle(id);
        if (!response) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body("ТС с id " + id + " удалено");
    }
}


