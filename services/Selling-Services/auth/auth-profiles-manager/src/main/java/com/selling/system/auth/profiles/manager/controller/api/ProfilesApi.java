package com.selling.system.auth.profiles.manager.controller.api;

import com.selling.system.auth.shared.module.entities.Profile;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public interface ProfilesApi {

    @GetMapping
    List<Profile> getProfiles();
}
