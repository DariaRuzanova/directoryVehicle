package com.example.directoryvehicle.entity;

import com.example.directoryvehicle.modele.TypeTransport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor
@Table(name = "vehicle",schema = "catalog_vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "mark", nullable = false,length = 50)
    private String mark;

    @Column(name = "model", nullable = false,length = 50)
    private String model;

    @Column(name = "category", nullable = false)
    private char category;

    @Column(name = "state_number", nullable = false,unique = true,length = 9)
    private String stateNumber;

    @Column(name = "year_release", nullable = false,length = 4)
    private int yearRelease;

    @Column(name = "type_transport", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeTransport typeTransport;
}
