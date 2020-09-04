package com.cdm.sig.services.components.utils;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.integrations.CIE10;
import com.cdm.sig.repositories.utils.CIE10Repository;
import com.cdm.sig.services.apis.utils.CIE10ServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CIE10ServiceImpl extends GenericServiceImpl<CIE10, String> implements CIE10ServiceAPI {
    @Autowired
    private CIE10Repository repository;

    @Override
    public JpaRepository<CIE10, String> getRepository() {
        return repository;
    }
}
