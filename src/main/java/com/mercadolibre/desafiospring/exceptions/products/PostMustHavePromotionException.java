package com.mercadolibre.desafiospring.exceptions.products;

import org.springframework.http.HttpStatus;

public class PostMustHavePromotionException extends PostException {
    public PostMustHavePromotionException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}
