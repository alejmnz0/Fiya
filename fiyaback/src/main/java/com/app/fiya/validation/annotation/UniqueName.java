package com.app.fiya.validation.annotation;

import com.app.fiya.validation.validator.UniqueDniValidator;
import com.app.fiya.validation.validator.UniqueNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNameValidator.class)
@Documented
public @interface UniqueName {

    String message() default "Name already registered";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
