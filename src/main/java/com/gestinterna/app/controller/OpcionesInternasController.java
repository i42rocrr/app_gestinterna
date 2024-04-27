package com.gestinterna.app.controller;



import com.gestinterna.app.model.Laboratorio_Pacientes;
import com.gestinterna.app.model.mysql.Pacientes;
import com.gestinterna.app.model.postgresql.Laboratorios;
import com.gestinterna.app.service.mysql.PacientesService;
import com.gestinterna.app.service.postgresql.LaboratoriosService;

import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.*;


@Controller
public class OpcionesInternasController {
    @Autowired
    private PacientesService pacientesService;

    @Autowired
    private LaboratoriosService laboratoriosService;

    private Context context;
    private Source source;


    //Inyección de la dependencia en el contexto de Spring Boot de "context" y "source",
    // elementos creados en el "ConfiguraPython".
    // Ahora, tanto "context" como "source", ya inicializados aquí y configurados en "ConfiguraPython"
    // pueden usarse para ejecutar python en GraalVM.
    public OpcionesInternasController(Context context_, Source source_) {
        this.context = context_;
        this.source = source_;
    }


    @GetMapping("/CrearListadoLaboratoriosPacientes")
    public String CrearListadosLaboratoriosPacientes(Model model) {

        Iterable<Pacientes> todosPacientes= pacientesService.listarTodos();
        List<Laboratorios> todosLaboratorios= laboratoriosService.listarTodos();

        //Es un array de registros del tipo "Laboratorio_Pacientes que almacena el listado
        // resultado de laboratorios_Pacientes que se va a pasar al "ListadoLaboratoriosPacientes.html"
        List<Laboratorio_Pacientes> laboratorioPacientes = new ArrayList<>();


        //Se recorren todos los laboratorios y para cada uno, se busca si tiene pacientes relacionados
        for (int i=0;i<todosLaboratorios.size();i++) {

            //Preparación para que este proceso de búsqueda, de los pacientes del laboratorio actual
            // "todosLaboratorios.get(i)", lo haga el programa python "ResultadosPyImpl.py"
            // Si encuentra pacientes que consumen alguno de los fármacos que éste fabrica, los añade
            // a la lista "pacientesEncontrados".
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

            //"pacientesEncontrados" tiene el listado de los pacientes que consumen los fármacos del
            // laboratorio actual de esta iteración "todosLaboratorios.get(i)"
            if (!pacientesEncontrados.isEmpty()) {
                // Se han encontrado pacientes del laboratorio "todosLaboratorios.get(i)".

                //Se recorren los pacientes encontrados y se meten en una array "listaPacientes"
                List<String> listaPacientes = new ArrayList<>();
                for (int j = 0; j < pacientesEncontrados.size(); j++) {
                    //Se añade el nombre del paciente en la lista "listaPacientes"
                    listaPacientes.add(pacientesEncontrados.get(j).toString());
                }

                //Se trata de añadir el elemento Laboratorio-ListaPacientes en el registro
                // "i_laboratorioPacientes".
                Laboratorio_Pacientes i_laboratorioPacientes = new Laboratorio_Pacientes(
                        todosLaboratorios.get(i).getNombre(),
                        listaPacientes
                );

                //Se añade al array de registros "laboratorioPacientes" el elemento "i_laboratorioPacientes"
                //que contiene el nombre del laboratorio actual y la lista de nombres de pacientes que
                //consumen sus fármacos.
                laboratorioPacientes.add(i_laboratorioPacientes);
            }
        }

        // Se añade el array de "laboratorioPacientes" a la Vista "ListadoLaboratoriosPacientes.html"
        // para que muestre por pantalla todos los laboratorios que tienen pacientes que consumen sus fármacos
        // y, de cada uno de estos laboratorios, su listado de los pacientes
        model.addAttribute("lab_pac", laboratorioPacientes);

        return "ListadoLaboratoriosPacientes";
    }
}
