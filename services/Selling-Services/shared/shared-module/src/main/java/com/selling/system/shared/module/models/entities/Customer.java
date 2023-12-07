package com.selling.system.shared.module.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements AbstractCustomer {

    private String gender;

    private int age;

    private String email;

    private int satisfaction;
}
