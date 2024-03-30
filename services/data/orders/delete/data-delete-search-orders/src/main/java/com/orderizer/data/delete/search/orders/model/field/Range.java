package com.orderizer.data.delete.search.orders.model.field;


import lombok.Data;

@Data
public class Range<T> {

    protected T min;

    protected T max;
}
