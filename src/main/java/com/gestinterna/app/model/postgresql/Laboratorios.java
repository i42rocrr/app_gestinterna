package com.gestinterna.app.model.postgresql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Laboratorios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nombre;

    @Column
    private String email;

    @ManyToMany (mappedBy = "laboratoriosList", fetch = FetchType.EAGER)
    private List<FarmacosLaboratorios> farmacosLaboratoriosList;

}