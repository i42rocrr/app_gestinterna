package com.gestinterna.app.resultadosPy;

import com.gestinterna.app.model.postgresql.FarmacosLaboratorios;
import com.gestinterna.app.model.postgresql.Laboratorios;

import java.util.List;

public record Laboratorio_Farmacos(
        Laboratorios laboratorio,
        List<FarmacosLaboratorios> farmacosLaboratorios
) {}
