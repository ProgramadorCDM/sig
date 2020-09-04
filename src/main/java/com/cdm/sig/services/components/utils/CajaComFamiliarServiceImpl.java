package com.cdm.sig.services.components.utils;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.integrations.CajaComFamiliar;
import com.cdm.sig.repositories.utils.CajaComFamiliarRepository;
import com.cdm.sig.services.apis.utils.CajaComFamiliarServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CajaComFamiliarServiceImpl extends GenericServiceImpl<CajaComFamiliar, String> implements CajaComFamiliarServiceAPI {

    @Autowired
    private CajaComFamiliarRepository repository;

    @Override
    public JpaRepository<CajaComFamiliar, String> getRepository() {
        return repository;
    }
}
