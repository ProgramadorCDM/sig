package com.cdm.sig.repositories;

import com.cdm.sig.models.Capacitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapacitacionRepository extends JpaRepository<Capacitacion, Long> {

    @Query("select c from Capacitacion c join fetch c.empleados e where e.cedula = ?1")
    List<Capacitacion> findCapacitacionByEmpleados(String cedula);

}
