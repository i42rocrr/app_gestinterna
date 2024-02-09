package com.gestinterna.app.service.postgresql;

import com.gestinterna.app.model.postgresql.Laboratorios;
import com.gestinterna.app.repository.postgresql.LaboratoriosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoriosServiceImpl implements LaboratoriosService {
    @Autowired
    private LaboratoriosRepository laboratoriosRepository;

    @Override
    public List<Laboratorios> listarTodos() {
        return laboratoriosRepository.findAll();
    }

}
