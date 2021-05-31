package com.mercadolibre.desafiospring.exceptions.user;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends UserException{

    private String message;

    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
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
