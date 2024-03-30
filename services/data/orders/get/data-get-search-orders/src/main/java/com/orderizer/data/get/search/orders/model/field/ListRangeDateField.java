package com.orderizer.data.get.search.orders.model.field;

import lombok.Data;

@Data
public class ListRangeDateField implements Field {

    private String field;

    private RangeDateField rangeDateField;
}
