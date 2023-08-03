package com.example.directoryvehicle;


import com.example.directoryvehicle.DTO.RequestVehicle;
import com.example.directoryvehicle.entity.Vehicle;
import com.example.directoryvehicle.modele.TypeTransport;
import com.example.directoryvehicle.repository.VehicleRepository;
import com.example.directoryvehicle.service.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DirectoryVehicleApplication.class)
@AutoConfigureMockMvc
public class VehicleServiceTest {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleService vehicleService;

    @Test
    public void addSuccessTest(){
        RequestVehicle requestVehicle = RequestVehicle.builder()
                .mark("ГАЗ")
                .model("CITY")
                .category('M')
                .stateNumber("P457KK163")
                .yearRelease(2020)
                .typeTransport(TypeTransport.BUS).build();

        boolean actual = vehicleService.addVehicle(requestVehicle);
        assertTrue(actual);
    }
    @Test
    public void getAllVehiclesTest(){
        List<Vehicle> list = vehicleRepository.findAll();
        List<Vehicle> actual = vehicleService.getAllVehicles();
        for(Vehicle vehicle:list){
            assertTrue(actual.stream().anyMatch(x-> Objects.equals(x.getStateNumber(),vehicle.getStateNumber())));
        }
    }


}
