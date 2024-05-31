package com.app.fiya.validation.annotation;

import com.app.fiya.validation.validator.ExeptionUniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExeptionUniqueEmailValidator.class)
@Documented
public @interface ExeptionUniqueEmail {

    String message() default "Email already registered";

    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String actualEmailField();
    String newEmailField();
}
