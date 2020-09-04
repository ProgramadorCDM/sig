package com.cdm.sig.services.components.utils;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.integrations.Eps;
import com.cdm.sig.repositories.utils.EpsRepository;
import com.cdm.sig.services.apis.utils.EpsServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EpsServiceImpl extends GenericServiceImpl<Eps, String> implements EpsServiceAPI {

    @Autowired
    private EpsRepository repository;


    @Override
    public JpaRepository<Eps, String> getRepository() {
        return repository;
    }
}
