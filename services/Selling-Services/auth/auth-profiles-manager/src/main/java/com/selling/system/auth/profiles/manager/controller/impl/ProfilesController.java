package com.selling.system.auth.profiles.manager.controller.impl;

import com.selling.system.auth.profiles.manager.controller.api.ProfilesApi;
import com.selling.system.auth.shared.module.entities.Profile;
import com.selling.system.auth.shared.module.repository.ProfilesRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProfilesController implements ProfilesApi {

    private final ProfilesRepository profilesRepository;

    public ProfilesController(ProfilesRepository profilesRepository) {
        this.profilesRepository = profilesRepository;
    }

    @Override
    public List<Profile> getProfiles() {
        return profilesRepository.findAll();
    }
}
