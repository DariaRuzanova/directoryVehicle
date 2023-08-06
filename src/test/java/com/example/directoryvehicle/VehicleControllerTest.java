package com.example.directoryvehicle;

import com.example.directoryvehicle.DTO.RequestVehicle;
import com.example.directoryvehicle.modele.TypeTransport;
import com.example.directoryvehicle.repository.VehicleRepository;
import com.example.directoryvehicle.service.VehicleService;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DirectoryVehicleApplication.class)
@AutoConfigureMockMvc
public class VehicleControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;
    private final Gson GSON = new Gson();

    @SneakyThrows
    @Test
    public void addVehicleTest(){
        RequestVehicle requestVehicle = RequestVehicle.builder()
                .mark("ГАЗ")
                .model("CITY")
                .category('M')
                .stateNumber("P455KE163")
                .yearRelease(2018)
                .typeTransport(TypeTransport.BUS).build();


        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GSON.toJson(requestVehicle));

        try {
            MvcResult result = mvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
        }catch (Exception e){
            long id = vehicleRepository.findVehicleByStateNumber("P455KE163").getId();
            vehicleRepository.deleteById(id);
        }


    }
}
