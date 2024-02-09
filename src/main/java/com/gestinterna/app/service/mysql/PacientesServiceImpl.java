package com.gestinterna.app.service.mysql;

import com.gestinterna.app.model.mysql.Pacientes;
import com.gestinterna.app.repository.mysql.PacientesRepository;
import com.gestinterna.app.service.mysql.PacientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacientesServiceImpl implements PacientesService {
    @Autowired
    private PacientesRepository pacientesRepository;

    public List<Pacientes> listarTodos() { return pacientesRepository.findAll(); }
}
