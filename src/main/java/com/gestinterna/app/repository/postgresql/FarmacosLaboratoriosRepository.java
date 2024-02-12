package com.gestinterna.app.repository.postgresql;

import com.gestinterna.app.model.postgresql.FarmacosLaboratorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmacosLaboratoriosRepository  extends JpaRepository<FarmacosLaboratorios,Long> {
}
