package com.cdm.sig.repositories;

import com.cdm.sig.models.Vacaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacacionesRepository extends JpaRepository<Vacaciones, Long> {

    @Query("select v from Vacaciones v join v.contrato c where c.empleado.cedula =?1")
    List<Vacaciones> findVacacionesByEmpleado(String cedula);

    @Query("select v.idVacaciones from Vacaciones v " +
            "join v.contrato c join c.empleado e where e.cedula=?1 and c.idContrato=?2 group by v.idVacaciones")
    Iterable<Long> findVacacionesTomadasByEmpleado(String cedula, Long idContrato);

}
