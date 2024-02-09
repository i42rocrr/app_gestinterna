package com.gestinterna.app.repository.postgresql;

import com.gestinterna.app.model.postgresql.Laboratorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoriosRepository extends JpaRepository<Laboratorios,Long> {
}
