package com.gestinterna.app.resultadosPy;

import com.gestinterna.app.model.mysql.FarmacosPacientes;
import com.gestinterna.app.model.mysql.Pacientes;

import java.util.List;

public record Paciente_Farmacos(
        String paciente,
        String farmaco
) {}