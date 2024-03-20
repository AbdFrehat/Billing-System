package com.orderizer.data.get.orders.manager.model.field;

import lombok.Data;

@Data
public class ListRangeField implements Field {

    private String field;

    private RangeField rangeField;
}
