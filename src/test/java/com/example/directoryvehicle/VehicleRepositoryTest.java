package com.example.directoryvehicle;

import com.example.directoryvehicle.entity.Vehicle;
import com.example.directoryvehicle.repository.VehicleRepository;
import org.checkerframework.checker.units.qual.N;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DirectoryVehicleApplication.class)
@AutoConfigureMockMvc
public class VehicleRepositoryTest {

    @Autowired
    VehicleRepository vehicleRepository;


    @Test
    public void findVehicleByMarkTest() {
        List<Vehicle> list = vehicleRepository.findVehicleByMark("ГАЗ");
        int actual = list.size();
        assertEquals(3, actual);
    }

    @Test
    public void findVehicleByModelTest() {
        List<Vehicle> list = vehicleRepository.findVehicleByModel("Ceed");
        assertFalse(list.isEmpty());
    }

    @Test
    public void findVehicleByYearReleaseTest() {
        List<Vehicle> list = vehicleRepository.findVehicleByYearRelease(2020);
        int actual = list.size();
        assertEquals(4, actual);
    }
    @Test
    public void findVehicleByCategoryTest(){
        List<Vehicle>list = vehicleRepository.findVehicleByCategory('N');
        int actual = list.size();
        assertEquals(3, actual);
    }
    @Test
    public void findVehicleByStateNumberTest(){
        Vehicle vehicle = vehicleRepository.findVehicleByStateNumber("C155HH164");
        long id = vehicle.getId();
        assertEquals(3,id);
    }
    @Test
    public void findVehicleByIdTest(){
        Optional<Vehicle> vehicle=vehicleRepository.findVehicleById(3L);
        assertEquals("C155HH164",vehicle.get().getStateNumber());
    }
}
