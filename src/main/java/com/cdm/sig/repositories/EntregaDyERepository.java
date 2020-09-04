package com.cdm.sig.repositories;

import com.cdm.sig.models.EntregaDyE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregaDyERepository extends JpaRepository<EntregaDyE, Long> {

    @Query("select e from EntregaDyE e join fetch e.empleado a where a.cedula =?1")
    List<EntregaDyE> findEntregaDyEByEmpleado(String cedula);

}
