package com.cdm.sig.repositories.utils;

import com.cdm.sig.models.integrations.CajaComFamiliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajaComFamiliarRepository extends JpaRepository<CajaComFamiliar, String> {
}
