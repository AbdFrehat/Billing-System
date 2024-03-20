package com.orderizer.data.get.orders.manager.model.field;

import lombok.Data;

@Data
public class ListMatchField implements Field {

    private String field;

    private MatchField matchField;
}
