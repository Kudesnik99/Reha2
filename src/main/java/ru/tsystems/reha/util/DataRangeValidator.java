package ru.tsystems.reha.util;

import ru.tsystems.reha.dto.TreatmentDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DataRangeValidator implements ConstraintValidator<DataRange, Object> {
    @Override
    public void initialize(DataRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        TreatmentDto treatmentDto = (TreatmentDto) o;
        if (treatmentDto.getPeriodStart() == null || treatmentDto.getPeriodEnd() == null) return true;
        return (treatmentDto.getPeriodStart().before(treatmentDto.getPeriodEnd()));
    }
}
