package com.gestinterna.app.repository.mysql;

import com.gestinterna.app.model.mysql.FarmacosPacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmacosPacientesRepository extends JpaRepository<FarmacosPacientes,Long> {
}
