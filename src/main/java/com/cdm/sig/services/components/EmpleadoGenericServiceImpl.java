package com.cdm.sig.services.components;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.Empleado;
import com.cdm.sig.repositories.EmpleadoRepository;
import com.cdm.sig.services.apis.EmpleadoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoGenericServiceImpl extends GenericServiceImpl<Empleado, String> implements EmpleadoServiceAPI {

    private final EmpleadoRepository repository;

    @Autowired
    public EmpleadoGenericServiceImpl(EmpleadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Empleado, String> getRepository() {
        return repository;
    }
}
