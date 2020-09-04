package com.cdm.sig.services.components;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.Documento;
import com.cdm.sig.repositories.DocumentoRepository;
import com.cdm.sig.services.apis.DocumentoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class DocumentoServiceImpl extends GenericServiceImpl<Documento, Long> implements DocumentoServiceAPI {

    private final DocumentoRepository repository;

    @Autowired
    public DocumentoServiceImpl(DocumentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Documento, Long> getRepository() {
        return repository;
    }

    @Override
    @NotNull
    @Transactional
    public List<Documento> findDocumentoByEmpleado(String cedula) {
        return repository.findDocumentoByEmpleado(cedula);
    }
}
