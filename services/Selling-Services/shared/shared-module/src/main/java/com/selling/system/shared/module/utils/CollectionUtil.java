package com.selling.system.shared.module.utils;

import java.util.Collection;
import java.util.Set;

public class CollectionUtil {

    public static <T extends Collection<?>> boolean isEmpty(T t) {
        return t == null || t.isEmpty();
    }
}
