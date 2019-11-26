package ru.tsystems.reha.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({TYPE,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DataRangeValidator.class)
@Documented
public @interface DataRange {
    String message() default "Treatment start date should be before then treatment finish!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
