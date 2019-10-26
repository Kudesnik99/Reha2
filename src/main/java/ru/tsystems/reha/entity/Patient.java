package ru.tsystems.reha.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

//import java.sql.Date;


@Entity
@Table(name ="patient")
@NamedQueries({
        @NamedQuery(name = "Patient.findByEmail", query = "select p from Patient p where p.email = :email")
})
public class Patient {
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


    @Id
    @Column(name = "patient_id")
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = " diagnosis")
    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Column(name = " insurance_num")
    public String getInsuranceNum() {
        return insuranceNum;
    }
    public void setInsuranceNum(String insuranceNum) {
        this.insuranceNum = insuranceNum;
    }

    @Column(name = "discharged", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public Boolean getDischarged() { return discharged; }
    public void setDischarged(Boolean discharged) { this.discharged = discharged; }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "date_start")
    public Date getDateStart() { return dateStart; }
    public void setDateStart(Date dateStart) { this.dateStart = dateStart; }

    @Column(name = "date_finish")
    public Date getDateFinish() { return dateFinish; }
    public void setDateFinish(Date dateFinish) { this.dateFinish = dateFinish; }

    //@Column(name = "doctor_id")
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "user_id", nullable = false)
    public User getDoctor() { return doctor; }
    public void setDoctor(User doctor) { this.doctor = doctor; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (patientId != patient.patientId) return false;
        if (firstName != null ? !firstName.equals(patient.firstName) : patient.firstName != null) return false;
        if (lastName != null ? !lastName.equals(patient.lastName) : patient.lastName != null) return false;
        if (diagnosis != null ? !diagnosis.equals(patient.diagnosis) : patient.diagnosis != null) return false;
        if (insuranceNum != null ? !insuranceNum.equals(patient.insuranceNum) : patient.insuranceNum != null)
            return false;
        if (discharged != null ? !discharged.equals(patient.discharged) : patient.discharged != null) return false;
        if (email != null ? !email.equals(patient.email) : patient.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = patientId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (diagnosis != null ? diagnosis.hashCode() : 0);
        result = 31 * result + (insuranceNum != null ? insuranceNum.hashCode() : 0);
        result = 31 * result + (discharged != null ? discharged.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
