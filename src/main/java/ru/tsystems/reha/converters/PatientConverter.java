package ru.tsystems.reha.converters;

import ru.tsystems.reha.dto.PatientDto;
import ru.tsystems.reha.entity.Patient;

public class PatientConverter {

    private PatientConverter() {
        // util class
    }

    public static Patient toPatient(PatientDto patient) {
        Patient result = new Patient();
        updatePatient(result, patient);
        return result;
    }

    public static PatientDto toPatientDto(Patient patient) {
        PatientDto result = new PatientDto();
        updatePatientDto(result, patient);
        return result;
    }

    public static void updatePatientDto(PatientDto patientDto, Patient patient) {
        patientDto.setPatientId(patient.getPatientId());
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setDiagnosis(patient.getDiagnosis());
        patientDto.setInsuranceNum(patient.getInsuranceNum());
        patientDto.setDischarged(patient.getDischarged());
        patientDto.setEmail(patient.getEmail());
        patientDto.setDateStart(patient.getDateStart());
        patientDto.setDateFinish(patient.getDateFinish());
    }

    public static void updatePatient(Patient patient, PatientDto patientDto) {
        patient.setPatientId(patientDto.getPatientId());
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setDiagnosis(patientDto.getDiagnosis());
        patient.setInsuranceNum(patientDto.getInsuranceNum());
        patient.setDischarged(patientDto.getDischarged());
        patient.setEmail(patientDto.getEmail());
        patient.setDateStart(patientDto.getDateStart());
        patient.setDateFinish(patientDto.getDateFinish());
    }

}
