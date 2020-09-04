package com.cdm.sig.services.apis;

import com.cdm.sig.core.archive.GenericServiceAPI;
import com.cdm.sig.models.EntregaDyE;

import java.util.List;

public interface EntregaDyEServiceAPI extends GenericServiceAPI<EntregaDyE, Long> {
    List<EntregaDyE> findEntregaDyEByEmpleado(String cedula);
}
