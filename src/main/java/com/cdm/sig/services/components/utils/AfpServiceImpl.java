package com.cdm.sig.services.components.utils;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.integrations.Afp;
import com.cdm.sig.repositories.utils.AfpRepository;
import com.cdm.sig.services.apis.utils.AfpServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AfpServiceImpl extends GenericServiceImpl<Afp, String> implements AfpServiceAPI {

    @Autowired
    private AfpRepository repository;

    @Override
    public JpaRepository<Afp, String> getRepository() {
        return repository;
    }
}
