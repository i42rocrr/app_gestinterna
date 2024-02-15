package com.gestinterna.app.service.mysql;

import com.gestinterna.app.model.mysql.Pacientes;
import com.gestinterna.app.repository.mysql.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacientesServiceImpl implements PacientesService {
    @Autowired
    private PacientesRepository pacientesRepository;

    public Iterable<Pacientes> listarTodos() { return pacientesRepository.findAll(); }
}
