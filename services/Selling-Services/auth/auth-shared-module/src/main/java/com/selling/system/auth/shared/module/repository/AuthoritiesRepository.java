package com.selling.system.auth.shared.module.repository;

import com.selling.system.auth.shared.module.models.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authority, Integer> {
}
