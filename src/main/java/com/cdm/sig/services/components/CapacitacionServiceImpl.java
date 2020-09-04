package com.cdm.sig.services.components;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.Capacitacion;
import com.cdm.sig.repositories.CapacitacionRepository;
import com.cdm.sig.services.apis.CapacitacionServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class CapacitacionServiceImpl extends GenericServiceImpl<Capacitacion, Long> implements CapacitacionServiceAPI {

    private final CapacitacionRepository repository;

    @Autowired
    public CapacitacionServiceImpl(CapacitacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Capacitacion, Long> getRepository() {
        return repository;
    }

    @Override
    @NotNull
    @Transactional
    public List<Capacitacion> findCapacitacionByEmpleados(String cedula) {
        return repository.findCapacitacionByEmpleados(cedula);
    }
}
