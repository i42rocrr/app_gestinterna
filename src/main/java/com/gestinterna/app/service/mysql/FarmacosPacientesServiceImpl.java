package com.gestinterna.app.service.mysql;

import com.gestinterna.app.model.mysql.FarmacosPacientes;
import com.gestinterna.app.repository.mysql.FarmacosPacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FarmacosPacientesServiceImpl implements FarmacosPacientesService {

    @Autowired
    private FarmacosPacientesRepository farmacosPacientesRepository;

    public List<FarmacosPacientes> listarTodos() {
        return farmacosPacientesRepository.findAll();
    }
}
