package ru.tsystems.reha.model;

import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.Treatment;

import java.util.Date;

public class TreatmentFormConverter {
    public static Treatment toTreatment(TreatmentForm treatmentForm) {
        Treatment result = new Treatment();
        result.setTreatmentId(treatmentForm.getTreatmentId());
        result.setTimePattern(treatmentForm.getTimePattern());
        result.setDescription(treatmentForm.getDescription());
        result.setPeriod_start(treatmentForm.getPeriod_start());
        result.setPeriod_end(treatmentForm.getPeriod_end());
        result.setDose(treatmentForm.getDose());
        result.setStatus(treatmentForm.getStatus());
        result.setTreatmentResult(treatmentForm.getTreatmentResult());
        result.setPatient(treatmentForm.getPatient());
        return result;
    }
}
