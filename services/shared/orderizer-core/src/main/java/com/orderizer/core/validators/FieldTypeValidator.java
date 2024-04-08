package com.orderizer.core.validators;

import com.orderizer.core.models.annotations.ValidFieldTypeEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class FieldTypeValidator implements ConstraintValidator<ValidFieldTypeEnum, String> {

    private List<String> enumConstants;

    @Override
    public void initialize(ValidFieldTypeEnum constraintAnnotation) {
        enumConstants = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants()).map(Enum::name).toList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return enumConstants.contains(value);
    }
}
