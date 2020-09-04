package com.cdm.sig.services.components;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.Examen;
import com.cdm.sig.repositories.ExamenRepository;
import com.cdm.sig.services.apis.ExamenServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class ExamenServiceImpl extends GenericServiceImpl<Examen, Long> implements ExamenServiceAPI {

    private final ExamenRepository repository;

    @Autowired
    public ExamenServiceImpl(ExamenRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Examen, Long> getRepository() {
        return repository;
    }

    @NotNull
    @Transactional
    @Override
    public List<Examen> findExamenByEmpleado(String cedula) {
        return repository.findExamenByEmpleado(cedula);
    }
}
