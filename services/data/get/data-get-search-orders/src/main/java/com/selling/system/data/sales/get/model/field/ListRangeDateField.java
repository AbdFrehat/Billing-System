package com.selling.system.data.sales.get.model.field;

import lombok.Data;

@Data
public class ListRangeDateField implements Field {

    private String field;

    private RangeDateField rangeDateField;
}
