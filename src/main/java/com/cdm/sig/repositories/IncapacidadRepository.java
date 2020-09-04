package com.cdm.sig.repositories;

import com.cdm.sig.models.Incapacidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncapacidadRepository extends JpaRepository<Incapacidad, Long> {

    @Query("select i from Incapacidad i join fetch i.empleado e where e.cedula = ?1")
    List<Incapacidad> findIncapacidadByEmpleado(String cedula);

}