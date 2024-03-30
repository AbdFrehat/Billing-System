package com.orderizer.data.get.orders.manager.model.field;


import lombok.Data;

@Data
public class Range<T> {

    protected T min;

    protected T max;
}
