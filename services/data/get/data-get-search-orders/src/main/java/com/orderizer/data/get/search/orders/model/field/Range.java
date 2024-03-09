package com.orderizer.data.get.search.orders.model.field;


import lombok.Data;

@Data
public class Range<T> {

    protected T min;

    protected T max;
}
