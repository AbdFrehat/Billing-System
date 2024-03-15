package com.orderizer.data.delete.orders.manager.model.field;

import lombok.Data;

@Data
public class ListRangeDateField implements Field {

    private String field;

    private RangeDateField rangeDateField;
}
