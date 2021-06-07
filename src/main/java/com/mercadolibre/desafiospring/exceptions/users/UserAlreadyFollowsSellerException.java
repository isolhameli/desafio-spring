package com.mercadolibre.desafiospring.exceptions.users;


import org.springframework.http.HttpStatus;

public class UserAlreadyFollowsSellerException extends UserException{

    public UserAlreadyFollowsSellerException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}