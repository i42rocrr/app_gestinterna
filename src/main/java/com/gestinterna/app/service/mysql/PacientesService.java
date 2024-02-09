package com.gestinterna.app.service.mysql;

import com.gestinterna.app.model.mysql.Pacientes;

import java.util.List;

public interface PacientesService {
    public List<Pacientes> listarTodos();
}
