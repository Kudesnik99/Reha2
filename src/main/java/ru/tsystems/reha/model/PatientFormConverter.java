package ru.tsystems.reha.model;

import ru.tsystems.reha.entity.Patient;

public class PatientFormConverter {
    public static Patient toPatient(PatientForm patient) {
        Patient result = new Patient();
        result.setPatientId(patient.getPatientId());
        result.setFirstName(patient.getFirstName());
        result.setLastName(patient.getLastName());
        result.setDiagnosis(patient.getDiagnosis());
        result.setInsuranceNum(patient.getInsuranceNum());
        result.setDischarged(patient.getDischarged());
        result.setEmail(patient.getEmail());
        result.setDateStart(patient.getDateStart());
        result.setDateFinish(patient.getDateFinish());
        return result;
    }
}
