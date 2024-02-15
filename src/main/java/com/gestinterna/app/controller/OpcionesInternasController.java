package com.gestinterna.app.controller;



import com.gestinterna.app.model.mysql.Pacientes;

import com.gestinterna.app.model.postgresql.Laboratorios;

import com.gestinterna.app.service.mysql.FarmacosPacientesService;
import com.gestinterna.app.service.mysql.PacientesService;
import com.gestinterna.app.service.postgresql.FarmacosLaboratoriosService;
import com.gestinterna.app.service.postgresql.LaboratoriosService;

import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.thymeleaf.engine.IterationStatusVar;


import java.util.*;


@Controller
public class OpcionesInternasController {
    @Autowired
    private PacientesService pacientesService;

    @Autowired
    private LaboratoriosService laboratoriosService;

    private Context context;
    private Source source;



    public OpcionesInternasController(Context context_, Source source_) {
        this.context = context_;
        this.source = source_;
    }


    @GetMapping("/CrearListadoLaboratoriosPacientes")
    public String CrearListadosLaboratoriosPacientes(Model model) {

        Iterable<Pacientes> todosPacientes= pacientesService.listarTodos();
        List<Laboratorios> todosLaboratorios= laboratoriosService.listarTodos();

        for (int i=0;i<todosLaboratorios.size();i++) {
            context
                    .getBindings("python")
                    .putMember("todosPacientes", todosPacientes);
            context
                    .getBindings("python")
                    .putMember("laboratorio", todosLaboratorios.get(i));

            context.eval(source);

            ArrayList pacientesEncontrados = context
                    .getBindings("python")
                    .getMember("pacientesEncontrados")
                    .as(ArrayList.class);

            if (!pacientesEncontrados.isEmpty()) {
                System.out.println("Laboratorio......: " + todosLaboratorios.get(i).getNombre());
                for (int j = 0; j < pacientesEncontrados.size(); j++) {
                    System.out.println("  -: " + pacientesEncontrados.get(j));
                }
            }
        }



        return "ListadoLaboratoriosPacientes";
    }
}
