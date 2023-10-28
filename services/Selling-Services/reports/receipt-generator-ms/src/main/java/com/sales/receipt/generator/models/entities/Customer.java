package com.sales.receipt.generator.models.entities;

import com.selling.shared.models.entities.AbstractCustomer;
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
