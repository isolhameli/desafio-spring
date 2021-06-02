package com.mercadolibre.desafiospring.exceptions.products;

import com.mercadolibre.desafiospring.exceptions.EntityException;

public class PostException extends EntityException {

    public PostException(String message, Integer status) {
        super(message, status);
    }
}
