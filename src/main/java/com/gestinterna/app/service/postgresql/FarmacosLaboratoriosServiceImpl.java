package com.gestinterna.app.service.postgresql;

import com.gestinterna.app.model.postgresql.FarmacosLaboratorios;
import com.gestinterna.app.model.postgresql.Laboratorios;
import com.gestinterna.app.repository.postgresql.FarmacosLaboratoriosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmacosLaboratoriosServiceImpl implements FarmacosLaboratoriosService {
    @Autowired
    private FarmacosLaboratoriosRepository farmacosLaboratoriosRepository;

    @Override
    public List<FarmacosLaboratorios> listarTodos() {
        return farmacosLaboratoriosRepository.findAll();
    }
}
