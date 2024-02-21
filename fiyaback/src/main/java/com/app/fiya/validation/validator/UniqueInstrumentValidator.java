package com.app.fiya.validation.validator;

import dam.salesianostriana.dam.GradesAPP.instrumento.service.InstrumentoService;
import dam.salesianostriana.dam.GradesAPP.validation.annotation.UniqueInstrument;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueInstrumentValidator implements ConstraintValidator<UniqueInstrument, String> {
    @Autowired
    private InstrumentoService service;


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !service.intrumentoExists(s);
    }
}
