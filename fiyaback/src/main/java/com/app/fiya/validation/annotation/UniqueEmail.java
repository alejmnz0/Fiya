package com.app.fiya.validation.annotation;

import com.app.fiya.validation.validator.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
public @interface UniqueEmail {

    String message() default "email already registered";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
