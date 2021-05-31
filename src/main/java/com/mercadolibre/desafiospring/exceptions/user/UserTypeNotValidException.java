package com.mercadolibre.desafiospring.exceptions.user;

import org.springframework.http.HttpStatus;

public class UserTypeNotValidException extends UserException{

    private String message;

    public UserTypeNotValidException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}