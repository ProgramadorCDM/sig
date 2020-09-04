package com.cdm.sig.services.components.utils;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.integrations.Arl;
import com.cdm.sig.repositories.utils.ArlRepository;
import com.cdm.sig.services.apis.utils.ArlServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ArlServiceImpl extends GenericServiceImpl<Arl, String> implements ArlServiceAPI {

    @Autowired
    private ArlRepository repository;

    @Override
    public JpaRepository<Arl, String> getRepository() {
        return repository;
    }
}
