package com.orderizer.data.delete.search.orders.model.field;


import lombok.Data;

@Data
public class ExactField implements Field {

    private String field;

    private Object value;

}
