package ru.tsystems.reha.model;

import ru.tsystems.reha.entity.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

public class TreatmentForm {

    private int treatmentId;
    private Pattern timePattern;
    private String description;
    private Date period_start;
    private Date period_end;
    private int dose;
    private TreatmentStatus status;
    private String treatmentResult;
    private Patient patient;
    private int patientId;
    private Remedy remedy;
    private int remedyId;

    public int getRemedyId() { return remedyId; }
    public void setRemedyId(int remedyId) { this.remedyId = remedyId; }

    public Remedy getRemedy() { return remedy; }
    public void setRemedy(Remedy remedy) { this.remedy = remedy; }

    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getTreatmentId() {
        return treatmentId;
    }
    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Pattern getTimePattern() {
        return timePattern;
    }
    public void setTimePattern(Pattern timePattern) {
        this.timePattern = timePattern;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPeriod_start() {
        return period_start;
    }
    public void setPeriod_start(Date period_start) {
        this.period_start = period_start;
    }

    public Date getPeriod_end() {
        return period_end;
    }
    public void setPeriod_end(Date period_end) {
        this.period_end = period_end;
    }

    public int getDose() {
        return dose;
    }
    public void setDose(int dose) {
        this.dose = dose;
    }

    public TreatmentStatus getStatus() {
        return status;
    }
    public void setStatus(TreatmentStatus status) {
        this.status = status;
    }

    public String getTreatmentResult() {
        return treatmentResult;
    }
    public void setTreatmentResult(String treatmentResult) {
        this.treatmentResult = treatmentResult;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreatmentForm that = (TreatmentForm) o;
        return treatmentId == that.treatmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(treatmentId);
    }
}
