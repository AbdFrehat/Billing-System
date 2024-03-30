package com.orderizer.data.orders.manager.model.field;


import lombok.Data;

@Data
public class Range<T> {

    protected T min;

    protected T max;
}
