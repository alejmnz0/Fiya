package com.app.fiya.validation.validator;

import com.app.fiya.user.service.UserService;
import com.app.fiya.validation.annotation.UniqueDni;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueDniValidator implements ConstraintValidator<UniqueDni, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.existDni(s);
    }
}
