package com.freeuni.coursewhisperer.data.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TextValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CorrectText {
    String message() default "Text contains forbidden words";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
