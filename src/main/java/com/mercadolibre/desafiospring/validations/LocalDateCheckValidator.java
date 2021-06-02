package com.mercadolibre.desafiospring.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class LocalDateCheckValidator
        implements ConstraintValidator<LocalDateCheck, String> {

    @Override public void initialize(LocalDateCheck ann) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Field cannot be null").addConstraintViolation();
            return false;
        }
        try {
            LocalDate date = LocalDate.parse(value,
                    DateTimeFormatter.ofPattern("dd-MM-uuuu")
                            .withResolverStyle(ResolverStyle.STRICT));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
