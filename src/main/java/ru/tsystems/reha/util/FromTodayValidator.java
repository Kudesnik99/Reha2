package ru.tsystems.reha.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class FromTodayValidator implements ConstraintValidator<FromToday, Object> {
    @Override
    public void initialize(FromToday constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Date periodStart = (Date)o;
        if (periodStart == null) return true;
        Long diff = periodStart.getTime() - new Date().getTime();
        return (diff > -43200000);
        //return !periodStart.before(new Date());
    }
}