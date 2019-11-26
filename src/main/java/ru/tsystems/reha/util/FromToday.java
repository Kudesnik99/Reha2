package ru.tsystems.reha.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = FromTodayValidator.class)
public @interface FromToday {
    String message() default "Treatment can start today or later!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
