package com.orderizer.data.delete.search.orders.model.field;

import lombok.Data;

@Data
public class ListRangeDateField implements Field {

    private String field;

    private RangeDateField rangeDateField;
}
