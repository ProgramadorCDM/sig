package com.cdm.sig.services.components;

import com.cdm.sig.core.archive.GenericServiceImpl;
import com.cdm.sig.models.Recommendation;
import com.cdm.sig.repositories.RecommendationRepository;
import com.cdm.sig.services.apis.RecommendationServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RecommendationServiceImpl extends GenericServiceImpl<Recommendation, Long> implements RecommendationServiceAPI {

    @Autowired
    private RecommendationRepository repository;

    @Override
    public JpaRepository<Recommendation, Long> getRepository() {
        return repository;
    }

}
