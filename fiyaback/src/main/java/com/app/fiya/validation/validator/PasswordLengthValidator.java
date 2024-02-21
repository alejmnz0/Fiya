package com.app.fiya.validation.validator;

import com.app.fiya.user.service.UserService;
import com.app.fiya.validation.annotation.PasswordLength;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class PasswordLengthValidator implements ConstraintValidator<PasswordLength, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(s) && (s.length()>8);
    }
}
