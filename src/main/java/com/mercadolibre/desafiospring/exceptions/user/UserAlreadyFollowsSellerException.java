package com.mercadolibre.desafiospring.exceptions.user;


import org.springframework.http.HttpStatus;

public class UserAlreadyFollowsSellerException extends UserException{

    private String message;

    public UserAlreadyFollowsSellerException(String message) {
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