package com.selling.system.auth.shared.module.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

import static com.selling.system.auth.shared.module.constants.Columns.Profile.PROFILE_ID;
import static com.selling.system.auth.shared.module.constants.Columns.Profile.PROFILE_NAME;


@Table(name = "profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Column(PROFILE_ID)
    @Id
    private int profileId;

    @Column(PROFILE_NAME)
    private String profileName;

    @Transient
    private Set<Authority> authorities;


}
