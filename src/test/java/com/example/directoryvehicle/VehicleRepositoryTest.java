package com.example.directoryvehicle;

import com.example.directoryvehicle.entity.Vehicle;
import com.example.directoryvehicle.repository.VehicleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


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
        assertEquals(3,actual);

    }
}
