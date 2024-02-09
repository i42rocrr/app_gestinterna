package com.gestinterna.app.service.postgresql;

import com.gestinterna.app.model.postgresql.Laboratorios;

import java.util.List;

public interface LaboratoriosService {
    public List<Laboratorios> listarTodos();
}
