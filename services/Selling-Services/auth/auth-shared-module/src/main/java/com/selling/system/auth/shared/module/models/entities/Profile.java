package com.selling.system.auth.shared.module.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "profiles")
@Data
public class Profile {

    @Column(name = "profile_id")
    @Id
    private int profileId;

    @Column(name = "profile_name")
    private String profileName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "profiles_authorities",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;
}
