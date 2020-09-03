package com.cdm.sig.repositories;

import com.cdm.sig.models.Integrations.ERole;
import com.cdm.sig.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
