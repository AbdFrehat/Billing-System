package com.orderizer.data.get.search.orders.model.field;

import lombok.Data;

@Data
public class ListMatchField implements Field {

    private String field;

    private MatchField matchField;
}
