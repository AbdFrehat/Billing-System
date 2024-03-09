package com.orderizer.data.get.search.orders.model.field;


import lombok.Data;

@Data
public class MatchField implements Field {

    private String field;

    private String value;

}
