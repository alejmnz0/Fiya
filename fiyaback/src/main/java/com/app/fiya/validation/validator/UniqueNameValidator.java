package com.app.fiya.validation.validator;

import com.app.fiya.team.service.TeamService;
import com.app.fiya.user.service.UserService;
import com.app.fiya.validation.annotation.UniqueDni;
import com.app.fiya.validation.annotation.UniqueName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    @Autowired
    private TeamService teamService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !teamService.existName(s);
    }
}
