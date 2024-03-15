package com.orderizer.data.delete.search.orders.model.field;

import lombok.Data;

@Data
public class ListExactField implements Field {

    private String field;

    private ExactField exactField;
}
