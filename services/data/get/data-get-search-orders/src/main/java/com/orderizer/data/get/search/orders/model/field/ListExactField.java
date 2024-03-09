package com.orderizer.data.get.search.orders.model.field;

import lombok.Data;

@Data
public class ListExactField implements Field {

    private String field;

    private ExactField exactField;
}
