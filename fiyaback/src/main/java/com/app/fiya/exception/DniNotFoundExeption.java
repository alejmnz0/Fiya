package com.app.fiya.exception;

import jakarta.persistence.EntityNotFoundException;

public class DniNotFoundExeption extends EntityNotFoundException{
    public DniNotFoundExeption(String dni){
        super("No user with DNI: " + dni);
    }
}
