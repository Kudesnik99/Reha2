package ru.tsystems.reha.dto;

import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.User;

import java.util.Date;

public class PatientDto {

    private int patientId;
    private String firstName;
    private String lastName;
    private String diagnosis;
    private String insuranceNum;
    private Boolean discharged;
    private String email;
    private Date dateStart;
    private Date dateFinish;
    private User doctor;

    public PatientDto(Patient patient) {
        this.patientId = patient.getPatientId();
        this.firstName = patient.getFirstName();
        this.lastName  = patient.getLastName();
        this.diagnosis = patient.getDiagnosis();
        this.insuranceNum = patient.getInsuranceNum();
        this.discharged = patient.getDischarged();
        this.email = patient.getEmail();
        this.dateStart = patient.getDateStart();
        this.dateFinish = patient.getDateFinish();
        this.doctor = patient.getDoctor();
    }


    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getInsuranceNum() {
        return insuranceNum;
    }
    public void setInsuranceNum(String insuranceNum) {
        this.insuranceNum = insuranceNum;
    }

    public Boolean getDischarged() { return discharged; }
    public void setDischarged(Boolean discharged) { this.discharged = discharged; }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateStart() { return dateStart; }
    public void setDateStart(Date dateStart) { this.dateStart = dateStart; }

    public Date getDateFinish() { return dateFinish; }
    public void setDateFinish(Date dateFinish) { this.dateFinish = dateFinish; }

    public User getDoctor() { return doctor; }
    public void setDoctor(User doctor) { this.doctor = doctor; }
}
