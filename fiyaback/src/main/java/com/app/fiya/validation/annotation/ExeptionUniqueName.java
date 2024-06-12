package com.app.fiya.validation.annotation;

import com.app.fiya.validation.validator.ExeptionUniqueEmailValidator;
import com.app.fiya.validation.validator.ExeptionUniqueNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExeptionUniqueNameValidator.class)
@Documented
public @interface ExeptionUniqueName {

    String message() default "Namer already registered";

    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String actualNameField();
    String newNameField();
}
