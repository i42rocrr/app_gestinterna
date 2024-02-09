package com.gestinterna.app.controller;


import com.gestinterna.app.model.mysql.Pacientes;
import com.gestinterna.app.service.mysql.PacientesService;
import com.gestinterna.app.service.postgresql.LaboratoriosService;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import org.graalvm.polyglot.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

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

        context.eval(source);

        //Iterable<Pacientes> listaPacientes = pacientesService.listarTodos();
        String variableJava = "HolaMundo";

        context
                .getBindings("python")
                .putMember("varJava", variableJava);


        return "ListadoLaboratoriosPacientes";
    }
}
