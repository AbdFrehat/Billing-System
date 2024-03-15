package com.orderizer.data.delete.orders.manager.model.field;

import lombok.Data;

@Data
public class ListExactField implements Field {

    private String field;

    private ExactField exactField;
}
