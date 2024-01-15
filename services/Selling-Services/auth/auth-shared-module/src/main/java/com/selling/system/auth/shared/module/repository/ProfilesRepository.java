package com.selling.system.auth.shared.module.repository;

import com.selling.system.auth.shared.module.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilesRepository extends JpaRepository<Profile, Integer> {
}
