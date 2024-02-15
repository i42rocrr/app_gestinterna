package com.gestinterna.app.service.mysql;

import com.gestinterna.app.model.mysql.Pacientes;

public interface PacientesService {
    public Iterable<Pacientes> listarTodos();
}
