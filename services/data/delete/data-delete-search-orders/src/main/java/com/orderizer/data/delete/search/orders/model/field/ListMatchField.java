package com.orderizer.data.delete.search.orders.model.field;

import lombok.Data;

@Data
public class ListMatchField implements Field {

    private String field;

    private MatchField matchField;
}
