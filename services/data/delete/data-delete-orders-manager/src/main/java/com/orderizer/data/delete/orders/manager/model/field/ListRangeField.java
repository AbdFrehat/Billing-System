package com.orderizer.data.delete.orders.manager.model.field;

import lombok.Data;

@Data
public class ListRangeField implements Field {

    private String field;

    private RangeField rangeField;
}
