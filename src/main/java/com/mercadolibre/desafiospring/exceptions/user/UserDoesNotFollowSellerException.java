package com.mercadolibre.desafiospring.exceptions.user;

import org.springframework.http.HttpStatus;

public class UserDoesNotFollowSellerException extends UserException{
    public UserDoesNotFollowSellerException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}
