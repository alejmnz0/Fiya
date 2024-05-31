package com.app.fiya.validation.validator;

import com.app.fiya.user.service.UserService;
import com.app.fiya.validation.annotation.ExeptionUniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class ExeptionUniqueEmailValidator implements ConstraintValidator<ExeptionUniqueEmail, Object> {

    private String actualEmailField;
    private String newEmailField;
    @Autowired
    private UserService userService;

    @Override
    public void initialize(ExeptionUniqueEmail constraintAnnotation) {
        this.actualEmailField = constraintAnnotation.actualEmailField();
        this.newEmailField = constraintAnnotation.newEmailField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String actualEmail = (String) PropertyAccessorFactory
                .forBeanPropertyAccess(o)
                .getPropertyValue(actualEmailField);

        String newEmail = (String) PropertyAccessorFactory
                .forBeanPropertyAccess(o)
                .getPropertyValue(newEmailField);

        return StringUtils.hasText(actualEmail) && actualEmail.contentEquals(newEmail);
    }

}
