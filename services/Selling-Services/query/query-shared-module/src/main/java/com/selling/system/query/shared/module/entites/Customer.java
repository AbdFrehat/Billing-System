package com.selling.system.query.shared.module.entites;

import com.selling.system.shared.models.entities.AbstractCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements AbstractCustomer {

    private String gender;

    private int age;

    private String email;

    private int satisfaction;
}
