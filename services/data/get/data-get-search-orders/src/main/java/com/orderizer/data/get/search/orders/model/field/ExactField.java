package com.orderizer.data.get.search.orders.model.field;


import lombok.Data;

@Data
public class ExactField implements Field {

    private String field;

    private Object value;

}
