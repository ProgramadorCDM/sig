package com.cdm.sig.services.components;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.Vacaciones;
import com.cdm.sig.repositories.VacacionesRepository;
import com.cdm.sig.services.apis.VacacionesServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class VacacionesServiceImpl extends GenericServiceImpl<Vacaciones, Long> implements VacacionesServiceAPI {

    private final VacacionesRepository repository;

    @Autowired
    public VacacionesServiceImpl(VacacionesRepository repository) {
        this.repository = repository;
    }


    @Override
    public JpaRepository<Vacaciones, Long> getRepository() {
        return repository;
    }

    @NotNull
    @Transactional
    @Override
    public List<Vacaciones> findVacacionesByEmpleado(String cedula) {
        return repository.findVacacionesByEmpleado(cedula);
    }

    @NotNull
    @Transactional
    @Override
    public Iterable<Long> findVacacionesTomadasByEmpleado(String cedula, Long idContrato) {
        return repository.findVacacionesTomadasByEmpleado(cedula, idContrato);
    }
}
