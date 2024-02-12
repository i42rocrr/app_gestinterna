package com.gestinterna.app.controller;


import com.gestinterna.app.model.mysql.FarmacosPacientes;
import com.gestinterna.app.model.mysql.Pacientes;
import com.gestinterna.app.model.postgresql.FarmacosLaboratorios;
import com.gestinterna.app.model.postgresql.Laboratorios;
import com.gestinterna.app.service.mysql.FarmacosPacientesService;
import com.gestinterna.app.service.mysql.PacientesService;
import com.gestinterna.app.service.postgresql.FarmacosLaboratoriosService;
import com.gestinterna.app.service.postgresql.LaboratoriosService;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.thymeleaf.engine.IterationStatusVar;

import java.util.ArrayList;
import java.util.List;


@Controller
public class OpcionesInternasController {

    @Autowired
    private FarmacosPacientesService farmacosPacientesService;
    @Autowired
    private PacientesService pacientesService;

    @Autowired
    private LaboratoriosService laboratoriosService;
    @Autowired
    private FarmacosLaboratoriosService farmacosLaboratoriosService;

    private Context context;
    private Source source;

    public OpcionesInternasController (Context context, Source source){
        this.context = context;
        this.source = source;
    }

    @GetMapping("/CrearListadoLaboratoriosPacientes")
    public String CrearListadosLaboratoriosPacientes(Model model) {


        List<Pacientes> todosPacientes= pacientesService.listarTodos();
        List<FarmacosPacientes> todosFarmacosPacientes= farmacosPacientesService.listarTodos();


        context
                .getBindings("python")
                .putMember("todosPacientes", todosPacientes);

        context
                .getBindings("python")
                .putMember("todosFarmacosPacientes", todosFarmacosPacientes);

        List<Laboratorios> todosLaboratorios= laboratoriosService.listarTodos();
        List<FarmacosLaboratorios> todosFarmacosLaboratorios= farmacosLaboratoriosService.listarTodos();
        context
                .getBindings("python")
                .putMember("todosLaboratorios", todosLaboratorios);

        context
                .getBindings("python")
                .putMember("todosFarmacosLaboratorios", todosFarmacosLaboratorios);

        /********* Traspaso de las listas al contexto Python:
                       - lista_LaboratorioFarmacos
                       - lista_PacienteFarmacos
                                                              ************************************/
        /*
        context
                .getBindings("python")
                .putMember("lista_PacienteFarmacos", lista_Pacientes);
        context
                .getBindings("python")
                .putMember("lista_LaboratorioFarmacos", lista_LaboratorioFarmacos);
        */
        /******************** Fin Traspaso de las listas al contexto Python ************************/



        /******************* Ejecución de Python y recepción de resultado *************************/
        context
                .eval(source);

        /*context
                .getBindings("python")
                .getMember("resultadoPython");*/
        /******************* Fin Ejecución de Python y recepción de resultado ********************/


        return "ListadoLaboratoriosPacientes";
    }
}
