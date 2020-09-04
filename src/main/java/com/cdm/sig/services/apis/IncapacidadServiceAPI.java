package com.cdm.sig.services.apis;

import com.cdm.sig.core.archive.GenericServiceAPI;
import com.cdm.sig.models.Incapacidad;

import java.util.List;

public interface IncapacidadServiceAPI extends GenericServiceAPI<Incapacidad, Long> {
    List<Incapacidad> findIncapacidadByEmpleado(String cedula);
}
