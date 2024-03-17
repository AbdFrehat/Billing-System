package com.orderizer.source.random.generator.orders.model.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

    private String gender;

    private int age;

    private String email;

    private int satisfaction;

}
