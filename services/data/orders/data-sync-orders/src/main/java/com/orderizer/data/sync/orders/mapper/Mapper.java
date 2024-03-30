package com.orderizer.data.sync.orders.mapper;

public interface Mapper<T, R> {

    R map(T t);
}
