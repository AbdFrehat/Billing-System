package com.selling.system.auth.shared.module.models.entities;

import com.selling.system.auth.shared.module.mapper.api.ProfileMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;


@Table(name = "profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Column("profile_id")
    @Id
    private int profileId;

    @Column("profile_name")
    private String profileName;

    @Transient
    private Set<Authority> authorities;


}
