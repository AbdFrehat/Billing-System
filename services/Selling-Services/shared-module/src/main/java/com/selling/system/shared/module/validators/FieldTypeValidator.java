package com.selling.system.shared.module.validators;

import com.selling.system.shared.module.models.annotations.ValidFieldTypeEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FieldTypeValidator implements ConstraintValidator<ValidFieldTypeEnum, Enum<?>> {

    private Pattern pattern;

    @Override
    public void initialize(ValidFieldTypeEnum constraintAnnotation) {
        try {
            pattern = Pattern.compile(constraintAnnotation.regexp());
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Given regex is invalid", e);
        }
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return pattern.matcher(value.name()).matches();
    }
}
