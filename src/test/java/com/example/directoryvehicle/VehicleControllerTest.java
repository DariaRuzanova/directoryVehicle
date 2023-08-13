package com.example.directoryvehicle;

import com.example.directoryvehicle.DTO.RequestVehicle;
import com.example.directoryvehicle.entity.Vehicle;
import com.example.directoryvehicle.modele.TypeTransport;
import com.example.directoryvehicle.repository.VehicleRepository;
import com.example.directoryvehicle.service.VehicleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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


    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private MockMvc mockMvc;


    private final Gson GSON = new Gson();

    @SneakyThrows
    @Test
    public void addVehicleTest() {
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
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            long id = vehicleRepository.findVehicleByStateNumber("P455KE163").getId();
            vehicleRepository.deleteById(id);
        }

    }

    @SneakyThrows
    @Test
    public void getAllVehicleTest() {
        var request = MockMvcRequestBuilders.get("/all");
        MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();

        List<Vehicle> listVehicle = mapper.readValue(response, new TypeReference<>() {
        });
        assertFalse(listVehicle.isEmpty());
    }

    @SneakyThrows
    @Test
    public void findVehicleByIdTest() {

        Mockito.when(this.vehicleService.getVehicleById(15L)).thenReturn(getVehicles().get(0));
        var request = MockMvcRequestBuilders.get("/find_by_id/15");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(15))
                .andExpect(jsonPath("$.stateNumber").value("X011AA157"));
    }

    @SneakyThrows
    @Test
    public void findVehicleByMarkTest() {
        Mockito.when(this.vehicleService.getVehiclesByMark("GAZ")).thenReturn(getVehicles());
        var request = MockMvcRequestBuilders.get("/find_by_mark?mark= GAZ");
        MvcResult result = mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();

        List<Vehicle> listVehicle = mapper.readValue(response, new TypeReference<>() {
        });
        assertFalse(listVehicle.isEmpty());
        assertEquals(listVehicle.size(),2);


    }


    private List<Vehicle> getVehicles() {
        Vehicle vehicle1 = Vehicle.builder()
                .id(15L)
                .mark("GAZ")
                .model("CITY")
                .category('M')
                .stateNumber("X011AA157")
                .yearRelease(2020)
                .typeTransport(TypeTransport.BUS).build();
        Vehicle vehicle2 = Vehicle.builder()
                .id(16L)
                .mark("Kamaz")
                .model("Kamaz 65207")
                .category('N')
                .stateNumber("A111AA177")
                .yearRelease(2018)
                .typeTransport(TypeTransport.CARGO).build();
        Vehicle vehicle3 = Vehicle.builder()
                .id(17L)
                .mark("GAZ")
                .model("Valday 8")
                .category('N')
                .stateNumber("X011CC158")
                .yearRelease(2020)
                .typeTransport(TypeTransport.CARGO).build();

        return List.of(vehicle1, vehicle2, vehicle3);
    }

}
