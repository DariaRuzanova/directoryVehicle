package com.example.directoryvehicle.repository;

import com.example.directoryvehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository <Vehicle,Long>{
    Optional<Vehicle> findVehicleByMark(String mark);
    Optional<Vehicle> findVehicleByYearRelease(int yearRelease);
    Optional<Vehicle> findVehicleByCategory(char category);
}
