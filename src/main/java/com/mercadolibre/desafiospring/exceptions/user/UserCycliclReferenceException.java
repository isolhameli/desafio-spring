package com.mercadolibre.desafiospring.exceptions.user;

import org.springframework.http.HttpStatus;

public class UserCycliclReferenceException extends UserException{


    public UserCycliclReferenceException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}
