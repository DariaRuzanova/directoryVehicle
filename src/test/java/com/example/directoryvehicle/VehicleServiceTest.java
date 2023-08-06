package com.example.directoryvehicle;


import com.example.directoryvehicle.DTO.RequestVehicle;
import com.example.directoryvehicle.entity.Vehicle;
import com.example.directoryvehicle.modele.TypeTransport;
import com.example.directoryvehicle.repository.VehicleRepository;
import com.example.directoryvehicle.service.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;

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
    public void updateTest(){
        Vehicle updatedVehicle = Vehicle.builder()
                .id(3L)
                .mark("KIA")
                .model("Rio")
                .category('M')
                .stateNumber("C152XX166")
                .yearRelease(2010)
                .typeTransport(TypeTransport.PASSENGER).build();
        vehicleService.update(updatedVehicle);
        Optional<Vehicle> actual = vehicleRepository.findVehicleById(3L);
        assertTrue(actual.isPresent() && actual.get().getStateNumber().equals("C152XX166"));
    }
    @Test
    public void deleteVehicleTest(){
        vehicleService.deleteVehicle(2L);
        assertNull(vehicleService.getVehicleById(2L));
    }
    @Test
    public void getAllVehiclesTest(){
        List<Vehicle> list = vehicleRepository.findAll();
        List<Vehicle> actual = vehicleService.getAllVehicles();
        for(Vehicle vehicle:list){
            assertTrue(actual.stream().anyMatch(x-> Objects.equals(x.getStateNumber(),vehicle.getStateNumber())));
        }
    }
    @Test
    public void getVehiclesByStateNumberTest(){
        String stateNumber = "A007AA177";
        Vehicle actual = vehicleService.getVehiclesByStateNumber(stateNumber);
        assertEquals(5L,actual.getId());
    }
    @Test
    public void getVehiclesByStateNumberExceptionTest(){
        String stateNumber = "A007AA170";
        Vehicle actual = vehicleService.getVehiclesByStateNumber(stateNumber);
        assertNull(actual);
    }
    @Test
    public void getVehiclesByMarkTest(){
        List<Vehicle> actual = vehicleService.getVehiclesByMark("KIA");
        assertNotNull(actual);
        Optional<Vehicle> vehicle = actual.stream().findFirst();
        assertTrue(vehicle.isPresent() && vehicle.get().getStateNumber().equals("C155HH164"));

    }
    @Test
    public void getVehiclesByModelTest(){
        List<Vehicle> actual = vehicleService.getVehiclesByModel("Ceed");
        assertEquals(1,actual.size());

    }
    @Test
    public void getVehiclesByCategoryTest(){
        List<Vehicle> actual = vehicleService.getVehiclesByCategory('M');
        assertNotNull(actual);
        Optional<Vehicle> vehicle = actual.stream().findFirst();
        assertTrue(vehicle.isPresent() && vehicle.get().getStateNumber().equals("C155HH164"));

    }
    @Test
    public void getVehicleByYearTest(){
        List<Vehicle> actual = vehicleService.getVehicleByYear(2016);
        assertNotNull(actual);
        Optional<Vehicle> vehicle = actual.stream().findFirst();
        assertTrue(vehicle.isPresent() && vehicle.get().getStateNumber().equals("C152KK166"));

    }
}
