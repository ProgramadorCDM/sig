package com.cdm.sig.services.apis;

import com.cdm.sig.core.archive.GenericServiceAPI;
import com.cdm.sig.models.Capacitacion;

import java.util.List;

public interface CapacitacionServiceAPI extends GenericServiceAPI<Capacitacion, Long> {
    List<Capacitacion> findCapacitacionByEmpleados(String cedula);
}
