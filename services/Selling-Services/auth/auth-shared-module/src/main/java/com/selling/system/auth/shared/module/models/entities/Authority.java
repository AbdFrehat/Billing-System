package com.selling.system.auth.shared.module.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Map;
import java.util.Objects;

@Table(name = "authorities")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @Column("authority_id")
    private int authorityId;

    @Column("authority_name")
    private String authorityName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return Objects.equals(authorityName, authority.authorityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorityName);
    }
}
