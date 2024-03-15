package com.orderizer.data.delete.orders.manager.model.field;


import lombok.Data;

@Data
public class MatchField implements Field {

    private String field;

    private String value;

}
