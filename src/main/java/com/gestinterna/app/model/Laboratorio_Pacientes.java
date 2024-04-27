package com.gestinterna.app.model;

import java.util.List;


//Registro creado para mostrar en la Vista el resultado de la relación "Laboratorios-Pacientes".
// Este proceso de búsqueda se hace en el controlador "OpcionesInternasController" en la función
// "CrearListadosLaboratoriosPacientes". Se crean tanto un array del tipo de este registro como
// un elemento de este tipo para almacenar 1 laboratorio y tantos pacientes tenga para
// añadir ese conjunto al array antes mencionado.
public record Laboratorio_Pacientes(
        String NombreLaboratorio,
        List<String> ListadoPacientes
) {}
