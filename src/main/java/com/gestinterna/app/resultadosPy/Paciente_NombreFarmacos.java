package com.gestinterna.app.resultadosPy;

import com.gestinterna.app.model.mysql.FarmacosPacientes;
import com.gestinterna.app.model.mysql.Pacientes;
import com.gestinterna.app.model.postgresql.FarmacosLaboratorios;

import java.util.List;

public record Paciente_NombreFarmacos(
        Pacientes paciente,
        List<FarmacosPacientes> farmacosPacientes
) {}
