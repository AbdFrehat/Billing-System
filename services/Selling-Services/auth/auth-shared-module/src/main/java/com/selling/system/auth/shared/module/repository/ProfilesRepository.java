package com.selling.system.auth.shared.module.repository;

import com.selling.system.auth.shared.module.models.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProfilesRepository extends JpaRepository<Profile, Integer> {

    Optional<Profile> findProfileByProfileName(String name);

}
