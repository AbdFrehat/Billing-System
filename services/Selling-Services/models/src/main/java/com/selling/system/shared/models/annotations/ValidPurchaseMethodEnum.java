package com.selling.system.shared.models.annotations;

import com.selling.system.shared.models.enums.PurchaseMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPurchaseMethodEnum {

    List<String> regexp = Arrays.stream(PurchaseMethod.values()).map(PurchaseMethod::getValue).toList();

    String message();
}
