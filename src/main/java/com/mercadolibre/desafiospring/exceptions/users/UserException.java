package com.mercadolibre.desafiospring.exceptions.users;

import com.mercadolibre.desafiospring.exceptions.EntityException;

public class UserException extends EntityException {

    public UserException(String message, Integer status) {
        super(message,status);
    }
}
