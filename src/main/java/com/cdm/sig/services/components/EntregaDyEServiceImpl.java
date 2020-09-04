package com.cdm.sig.services.components;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.EntregaDyE;
import com.cdm.sig.repositories.EntregaDyERepository;
import com.cdm.sig.services.apis.EntregaDyEServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaDyEServiceImpl extends GenericServiceImpl<EntregaDyE, Long> implements EntregaDyEServiceAPI {

    private final EntregaDyERepository repository;

    @Autowired
    public EntregaDyEServiceImpl(EntregaDyERepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<EntregaDyE, Long> getRepository() {
        return repository;
    }

    @Override
    public List<EntregaDyE> findEntregaDyEByEmpleado(String cedula) {
        return repository.findEntregaDyEByEmpleado(cedula);
    }
}
