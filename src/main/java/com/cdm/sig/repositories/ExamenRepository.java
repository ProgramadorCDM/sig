package com.cdm.sig.repositories;

import com.cdm.sig.models.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {

    @Query("select e from Examen e join fetch e.contrato c where c.empleado.cedula = ?1")
    List<Examen> findExamenByEmpleado(String cedula);

}
