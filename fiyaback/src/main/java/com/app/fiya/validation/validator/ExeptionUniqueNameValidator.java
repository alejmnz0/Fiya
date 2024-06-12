package com.app.fiya.validation.validator;

import com.app.fiya.team.service.TeamService;
import com.app.fiya.user.service.UserService;
import com.app.fiya.validation.annotation.ExeptionUniqueName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class ExeptionUniqueNameValidator implements ConstraintValidator<ExeptionUniqueName, Object> {

    private String actualNameField;
    private String newNameField;
    @Autowired
    private TeamService teamService;

    @Override
    public void initialize(ExeptionUniqueName constraintAnnotation) {
        this.actualNameField = constraintAnnotation.actualNameField();
        this.newNameField = constraintAnnotation.newNameField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String actualName = (String) PropertyAccessorFactory
                .forBeanPropertyAccess(o)
                .getPropertyValue(actualNameField);

        String newName = (String) PropertyAccessorFactory
                .forBeanPropertyAccess(o)
                .getPropertyValue(newNameField);

        if (StringUtils.hasText(newName) && !newName.equals(actualName)) {
            // Check if the new email is already used by another user
            return !teamService.nameExist(newName);
        }

        // If the new email is the same as the actual email or actual email is blank, return true
        return true;
    }

}
