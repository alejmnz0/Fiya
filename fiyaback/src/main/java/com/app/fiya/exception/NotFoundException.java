package com.app.fiya.exception;

import jakarta.persistence.EntityNotFoundException;

public class NotFoundException extends EntityNotFoundException{
    public NotFoundException(String entity){
        super("The "+ entity +" or the list could not be found");
    }
}
