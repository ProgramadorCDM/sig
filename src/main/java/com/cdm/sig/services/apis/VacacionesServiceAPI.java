package com.cdm.sig.services.apis;

import com.cdm.sig.core.archive.GenericServiceAPI;
import com.cdm.sig.models.Vacaciones;

import java.util.List;

public interface VacacionesServiceAPI extends GenericServiceAPI<Vacaciones, Long> {
    List<Vacaciones> findVacacionesByEmpleado(String cedula);

    Iterable<Long> findVacacionesTomadasByEmpleado(String cedula, Long idContrato);
}
