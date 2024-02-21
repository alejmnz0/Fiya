package com.app.fiya.validation.validator;

import com.app.fiya.validation.annotation.LocalDateNotNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class LocalDateNotNullValidator implements ConstraintValidator<LocalDateNotNull, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value != null;
    }
}
