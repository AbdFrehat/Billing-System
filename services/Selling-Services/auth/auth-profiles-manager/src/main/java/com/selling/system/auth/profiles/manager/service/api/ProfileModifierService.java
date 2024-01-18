package com.selling.system.auth.profiles.manager.service.api;

import com.selling.system.auth.shared.module.models.entities.Profile;
import com.selling.system.auth.shared.module.models.request.ProfileUpdateRequest;

public interface ProfileModifierService {

    Profile modifyProfile(ProfileUpdateRequest profileUpdateRequest, Profile profile);
}
