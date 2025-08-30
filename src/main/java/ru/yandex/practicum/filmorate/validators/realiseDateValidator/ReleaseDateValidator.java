package ru.yandex.practicum.filmorate.validators.realiseDateValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ReleaseDateValidator implements ConstraintValidator<ru.yandex.practicum.filmorate.validators.realiseDateValidator.CustomReleaseDateMin, LocalDate> {

    private LocalDate minDate;

    @Override
    public void initialize(ru.yandex.practicum.filmorate.validators.realiseDateValidator.CustomReleaseDateMin constraintAnnotation) {
        minDate = LocalDate.parse(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext context) {
        return localDate.isAfter(minDate);
    }
}