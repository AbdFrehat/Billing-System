package com.sales.fetch.items.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String gender;
    private int age;
    private String email;
    private int satisfaction;
}
