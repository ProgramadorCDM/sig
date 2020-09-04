package com.cdm.sig.services.components;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.Foto;
import com.cdm.sig.repositories.FotoRepository;
import com.cdm.sig.services.apis.FotoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FotoServiceImpl extends GenericServiceImpl<Foto, String> implements FotoServiceAPI {

    @Autowired
    private FotoRepository repository;

    @Override
    public JpaRepository<Foto, String> getRepository() {
        return repository;
    }
}
