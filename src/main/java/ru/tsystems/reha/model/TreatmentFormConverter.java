package ru.tsystems.reha.model;

import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.Remedy;
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
        result.setRemedy(treatmentForm.getRemedy());
        return result;
    }

    public static TreatmentForm toTreatmentForm (Treatment treatment) {
        TreatmentForm result = new TreatmentForm();
        result.setTreatmentId(treatment.getTreatmentId());
        result.setTimePattern(treatment.getTimePattern());
        result.setDescription(treatment.getDescription());
        result.setPeriod_start(treatment.getPeriod_start());
        result.setPeriod_end(treatment.getPeriod_end());
        result.setDose(treatment.getDose());
        result.setStatus(treatment.getStatus());
        result.setTreatmentResult(treatment.getTreatmentResult());
        result.setPatient(treatment.getPatient());
        result.setPatientId(treatment.getPatient().getPatientId());
        result.setRemedy(treatment.getRemedy());
        result.setRemedyId(treatment.getRemedy().getRemedyId());
        return result;
    }
}
