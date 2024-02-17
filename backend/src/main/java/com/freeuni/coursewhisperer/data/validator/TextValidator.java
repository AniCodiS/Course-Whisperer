package com.freeuni.coursewhisperer.data.validator;

import com.freeuni.coursewhisperer.config.Config;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@SuppressWarnings("unchecked")
public class TextValidator implements ConstraintValidator<CorrectText, String> {
    @Override
    public void initialize(CorrectText constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        for (String word : Config.Constants.FORBIDDEN_WORDS) {
            if (value.toLowerCase().contains(word.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}