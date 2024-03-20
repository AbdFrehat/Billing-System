package com.orderizer.data.orders.manager.model.field;


import lombok.Data;

@Data
public class ExactField implements Field {

    private String field;

    private Object value;

}
