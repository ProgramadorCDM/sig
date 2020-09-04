package com.cdm.sig.repositories.utils;

import com.cdm.sig.models.integrations.CIE10;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CIE10Repository extends JpaRepository<CIE10, String> {
}
