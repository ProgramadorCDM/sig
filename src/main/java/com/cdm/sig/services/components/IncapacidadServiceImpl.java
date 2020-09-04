package com.cdm.sig.services.components;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.Incapacidad;
import com.cdm.sig.repositories.IncapacidadRepository;
import com.cdm.sig.services.apis.IncapacidadServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncapacidadServiceImpl extends GenericServiceImpl<Incapacidad, Long> implements IncapacidadServiceAPI {

    private final IncapacidadRepository repository;

    @Autowired
    public IncapacidadServiceImpl(IncapacidadRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Incapacidad, Long> getRepository() {
        return repository;
    }

    @Override
    public List<Incapacidad> findIncapacidadByEmpleado(String cedula) {
        return repository.findIncapacidadByEmpleado(cedula);
    }
}
