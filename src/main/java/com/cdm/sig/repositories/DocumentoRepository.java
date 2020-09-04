package com.cdm.sig.repositories;

import com.cdm.sig.models.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    @Query("select d from Documento d join fetch d.empleado e where e.cedula = ?1")
    List<Documento> findDocumentoByEmpleado(String cedula);
}
