package com.gestinterna.app.service.mysql;

import com.gestinterna.app.model.mysql.FarmacosPacientes;

import java.util.List;

public interface FarmacosPacientesService {
    public List<FarmacosPacientes> listarTodos();
}
