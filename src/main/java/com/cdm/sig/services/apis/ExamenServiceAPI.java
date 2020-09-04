package com.cdm.sig.services.apis;

import com.cdm.sig.core.archive.GenericServiceAPI;
import com.cdm.sig.models.Examen;

import java.util.List;

public interface ExamenServiceAPI extends GenericServiceAPI<Examen, Long> {
    List<Examen> findExamenByEmpleado(String cedula);
}
