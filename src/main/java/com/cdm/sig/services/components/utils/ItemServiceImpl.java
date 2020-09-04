package com.cdm.sig.services.components.utils;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.integrations.Items;
import com.cdm.sig.repositories.utils.ItemsRepository;
import com.cdm.sig.services.apis.utils.ItemServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends GenericServiceImpl<Items, Long> implements ItemServiceAPI {

    @Autowired
    private ItemsRepository repository;

    @Override
    public JpaRepository<Items, Long> getRepository() {
        return repository;
    }
}
