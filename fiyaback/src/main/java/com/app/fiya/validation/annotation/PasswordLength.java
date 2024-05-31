package com.app.fiya.validation.annotation;

import com.app.fiya.validation.validator.PasswordLengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordLengthValidator.class)
@Documented
public @interface PasswordLength {

    String message() default "Password too short";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
