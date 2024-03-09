package com.selling.system.data.sales.get.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.TextIndexed;

@Data
public class Customer {

    private String gender;

    private int age;

    private String email;

    private int satisfaction;

}
