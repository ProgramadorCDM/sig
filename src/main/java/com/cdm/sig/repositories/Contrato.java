package com.cdm.sig.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Contrato extends JpaRepository<Contrato, Long> {
}
