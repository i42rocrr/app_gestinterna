package com.gestinterna.app.resultadosPy;

import com.gestinterna.app.model.mysql.Pacientes;
import com.gestinterna.app.model.postgresql.Laboratorios;

public record LaboratorioPacientes(
        Laboratorios laboratorio,
        Iterable<Pacientes> pacientesList
) {

}
