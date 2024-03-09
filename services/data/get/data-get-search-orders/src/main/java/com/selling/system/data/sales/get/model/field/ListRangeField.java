package com.selling.system.data.sales.get.model.field;

import lombok.Data;

@Data
public class ListRangeField implements Field {

    private String field;

    private RangeField rangeField;
}
