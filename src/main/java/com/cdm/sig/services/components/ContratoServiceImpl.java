package com.cdm.sig.services.components;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.Contrato;
import com.cdm.sig.repositories.ContratoRepository;
import com.cdm.sig.services.apis.ContratoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ContratoServiceImpl extends GenericServiceImpl<Contrato, Long> implements ContratoServiceAPI {

    private final ContratoRepository repository;

    @Autowired
    public ContratoServiceImpl(ContratoRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Contrato, Long> getRepository() {
        return repository;
    }
}
