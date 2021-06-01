package com.mercadolibre.desafiospring.exceptions.user;

import org.springframework.http.HttpStatus;

public class UserTypeNotValidException extends UserException{

    public UserTypeNotValidException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}