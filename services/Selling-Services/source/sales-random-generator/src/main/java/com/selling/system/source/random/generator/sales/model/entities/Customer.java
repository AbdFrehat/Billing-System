package com.selling.system.source.random.generator.sales.model.entities;

import com.selling.system.shared.models.entities.AbstractCustomer;
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
