package com.app.fiya.validation.annotation;

import com.app.fiya.validation.validator.UniqueDniValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDniValidator.class)
@Documented
public @interface UniqueDni {

    String message() default "DNI already registered";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
