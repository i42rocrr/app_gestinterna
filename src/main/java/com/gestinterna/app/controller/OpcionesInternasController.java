package com.gestinterna.app.controller;


import com.gestinterna.app.model.mysql.FarmacosPacientes;
import com.gestinterna.app.model.mysql.Pacientes;
import com.gestinterna.app.model.postgresql.FarmacosLaboratorios;
import com.gestinterna.app.model.postgresql.Laboratorios;
import com.gestinterna.app.resultadosPy.LaboratorioPacientes;
import com.gestinterna.app.resultadosPy.Laboratorio_NombreFarmacos;
import com.gestinterna.app.resultadosPy.Paciente_NombreFarmacos;
import com.gestinterna.app.service.mysql.PacientesService;
import com.gestinterna.app.service.postgresql.LaboratoriosService;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;


@Controller
public class OpcionesInternasController {

    @Autowired
    private PacientesService pacientesService;

    @Autowired
    private LaboratoriosService laboratoriosService;

    private Context context;
    private Source source;

    public OpcionesInternasController (Context context, Source source){
        this.context = context;
        this.source = source;
    }

    @GetMapping("/CrearListadoLaboratoriosPacientes")
    public String CrearListadosLaboratoriosPacientes(Model model) {

        /*********** Crear listado Laboratorio - Nombre de fármacos ************/
        // Crear y rellenar "lista_laboratorio_nombreFarmacos" del tipo "List<Laboratorio_NombreFarmacos>"
        // Se pasará a Python el objeto lista_laboratorio_nombreFarmacos
        List<Laboratorios> todosLaboratorios = laboratoriosService.listarTodos();
        List<Laboratorio_NombreFarmacos> lista_laboratorio_nombreFarmacos = new ArrayList<>();


        for (int i=0; i<todosLaboratorios.size(); i++) {
            Laboratorios laboratorioElemento = todosLaboratorios.get(i);

            List<FarmacosLaboratorios> listaFarmacosLaboratorio = new ArrayList<>(
                    laboratorioElemento.getFarmacosLaboratoriosList()
            );
            Laboratorio_NombreFarmacos laboratorio_nombreFarmacos_Elemento = new Laboratorio_NombreFarmacos(
                    laboratorioElemento,
                    listaFarmacosLaboratorio
            );

            lista_laboratorio_nombreFarmacos.add(
                    laboratorio_nombreFarmacos_Elemento
            );
        }



        /*********** Crear listado Pacientes - Nombre de fármacos ************/
        // Crear y rellenar "lista_paciente_nombreFarmacos" del tipo "List<Paciente_NombreFarmacos>"
        // Se pasará a Python el objeto lista_paciente_nombreFarmacos
        List<Pacientes> todosPacientes = pacientesService.listarTodos();
        List<Paciente_NombreFarmacos> lista_paciente_nombreFarmacos = new ArrayList<>();


        for (int i=0; i<todosPacientes.size(); i++) {
            Pacientes pacienteElemento = todosPacientes.get(i);

            List<FarmacosPacientes> listaFarmacosPaciente = new ArrayList<>(
                    pacienteElemento.getFarmacosPacientesList()
            );
            Paciente_NombreFarmacos paciente_nombreFarmacos_Elemento = new Paciente_NombreFarmacos(
                    pacienteElemento,
                    listaFarmacosPaciente
            );

            lista_paciente_nombreFarmacos.add(
                    paciente_nombreFarmacos_Elemento
            );
        }

        context
                .getBindings("python")
                .putMember("todosPacienteFarmacos", lista_paciente_nombreFarmacos);
        context
                .getBindings("python")
                .putMember("todosLaboratorioFarmacos", lista_laboratorio_nombreFarmacos);

        context
                .eval(source);

        context
                .getBindings("python")
                .getMember("resultadoPython");



        return "ListadoLaboratoriosPacientes";
    }
}
