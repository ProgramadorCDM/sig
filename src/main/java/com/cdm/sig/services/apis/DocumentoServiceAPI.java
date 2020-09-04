package com.cdm.sig.services.apis;


import com.cdm.sig.core.archive.GenericServiceAPI;
import com.cdm.sig.models.Documento;

import java.util.List;

public interface DocumentoServiceAPI extends GenericServiceAPI<Documento, Long> {
    List<Documento> findDocumentoByEmpleado(String cedula);
}
