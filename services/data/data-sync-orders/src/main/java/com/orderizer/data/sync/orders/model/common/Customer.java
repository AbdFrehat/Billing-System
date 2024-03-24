package com.orderizer.data.sync.orders.model.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Customer {

    private String gender;

    private int age;

    private String email;

    private int satisfaction;

}
