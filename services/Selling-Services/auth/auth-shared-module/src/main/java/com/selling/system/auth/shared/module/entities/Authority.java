package com.selling.system.auth.shared.module.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "authorities")
@Data
public class Authority {

    @Id
    @Column(name = "authority_id")
    private int authorityId;

    @Column(name = "authority_name")
    private String authorityName;


}
