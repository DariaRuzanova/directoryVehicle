package com.example.directoryvehicle.repository;

import com.example.directoryvehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findVehicleByMark(String mark);

    List<Vehicle> findVehicleByModel(String model);

    Optional<Vehicle> findVehicleByYearRelease(int yearRelease);

    List<Vehicle> findVehicleByCategory(char category);

    Vehicle findVehicleByStateNumber(String stateNumber);

    Optional<Vehicle> findVehicleById(Long id);
}
