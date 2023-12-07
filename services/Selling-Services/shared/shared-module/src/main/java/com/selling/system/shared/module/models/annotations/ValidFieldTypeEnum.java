package com.selling.system.shared.module.models.annotations;

import com.selling.system.shared.module.validators.FieldTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldTypeValidator.class)
public @interface ValidFieldTypeEnum {

    String regexp();
    
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
