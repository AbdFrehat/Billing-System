package com.orderizer.core.utils;

import java.util.Collection;

public class CollectionUtil {

    public static <T extends Collection<?>> boolean isEmpty(T t) {
        return t == null || t.isEmpty();
    }
}
