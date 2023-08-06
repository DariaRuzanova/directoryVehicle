package com.example.directoryvehicle.DTO;

import com.example.directoryvehicle.modele.TypeTransport;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@AllArgsConstructor
@Data
@Builder
public class RequestVehicle {
    @NotNull
    @NotEmpty
    private String mark;

    @NotBlank
    @NotEmpty
    private String model;

    @NotNull
    private char category;

//    @UniqueElements
    @NotEmpty
    private String stateNumber;

    @NotNull
    private int yearRelease;

    @NotNull
    private TypeTransport typeTransport;

}
