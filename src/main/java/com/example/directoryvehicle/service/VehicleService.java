package com.example.directoryvehicle.service;

import com.example.directoryvehicle.DTO.RequestVehicle;
import com.example.directoryvehicle.entity.Vehicle;
import com.example.directoryvehicle.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VehicleService {
    private VehicleRepository vehicleRepository;

    public boolean addVehicle(RequestVehicle requestVehicle) {
        String stateNumber = requestVehicle.getStateNumber();
        boolean flag = true;
        if (vehicleRepository.findVehicleByStateNumber(stateNumber)!=null) {
            flag = false;
            log.error("Транспортное средство с номером {} есть в базе данных", stateNumber);
        }
        Vehicle vehicle = Vehicle.builder().mark(requestVehicle.getMark())
                .model(requestVehicle.getModel())
                .category(requestVehicle.getCategory())
                .stateNumber(stateNumber)
                .yearRelease(requestVehicle.getYearRelease())
                .typeTransport(requestVehicle.getTypeTransport())
                .build();

        vehicleRepository.save(vehicle);
        log.info("Данные транспортного средства с регистрационным номером {} успешно загружены в базу", stateNumber);
        return flag;

    }


    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        log.info("Выведен список всех товаров");
        return vehicleList;
    }

    public Vehicle getVehicleById(Long id) {
        if (vehicleRepository.findVehicleById(id).isPresent()) {
            log.info("Выведено ТС с id {}", id);
            return vehicleRepository.findVehicleById(id).get();
        }
        log.warn("ТС с id {} нет в базе", id);
        return null;
    }

    public List<Vehicle> getVehiclesByMark(String mark) {
        List<Vehicle> listVehicleByMark = vehicleRepository.findVehicleByMark(mark);
        if (listVehicleByMark.isEmpty()) {
            log.warn("Таких марок в каталоге нет");
            return null;
        }
        log.info("ТС отфильтрованы по марке {}", mark);
        return listVehicleByMark;
    }

    public List<Vehicle> getVehiclesByModel(String model) {
        List<Vehicle> listVehicleByModel = vehicleRepository.findVehicleByModel(model);
        if (listVehicleByModel.isEmpty()) {
            log.warn("Таких моделей в каталоге нет");
            return null;
        }
        log.info("ТС отфильтрованы по модели {}", model);
        return listVehicleByModel;
    }

    public List<Vehicle> getVehiclesByCategory(char category) {
        List<Vehicle> listVehicleByCategory = vehicleRepository.findVehicleByCategory(category);
        if (listVehicleByCategory.isEmpty()) {
            log.warn("ТС с такой категорией в каталоге нет");
            return null;
        }
        log.info("ТС отфильтрованы по категории {}", category);
        return listVehicleByCategory;
    }

    public Vehicle getVehiclesByStateNumber(String stateNumber) {
        if (vehicleRepository.findVehicleByStateNumber(stateNumber) != null) {
            log.info("Выведено ТС с номером {}", stateNumber);
            return vehicleRepository.findVehicleByStateNumber(stateNumber);
        }
        log.warn("ТС с номером {} нет в базе", stateNumber);
        return null;
    }
}

