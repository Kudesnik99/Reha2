package ru.tsystems.reha.dto;

import java.util.Date;

public class PatientDto {
    private Long patientId;

    private String firstName;       // Name

    private String lastName;        // Surname

    private String diagnosis;

    private String email;

    private String insuranceNum;

    private Boolean discharged;

    private Date dateStart;         // Admitted for treatment

    private Date dateFinish;        // Date of discharging

    private UserDto doctor;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInsuranceNum() {
        return insuranceNum;
    }

    public void setInsuranceNum(String insuranceNum) {
        this.insuranceNum = insuranceNum;
    }

    public Boolean getDischarged() {
        return discharged;
    }

    public void setDischarged(Boolean discharged) {
        this.discharged = discharged;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public UserDto getDoctor() {
        return doctor;
    }

    public void setDoctor(UserDto doctor) {
        this.doctor = doctor;
    }
}
