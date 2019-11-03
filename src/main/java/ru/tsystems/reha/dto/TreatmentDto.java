package ru.tsystems.reha.dto;

import ru.tsystems.reha.entity.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TreatmentDto {

    private int treatmentId;
    private String description;
    private String period_start;
    private String period_end;
    private int dose;
    private TreatmentStatus status;
    private String treatmentResult;
    private Patient patient;
    private Remedy remedy;
    private Pattern timePattern;

    public TreatmentDto(Treatment treatment) {
        this.treatmentId = treatment.getTreatmentId();
        this.description = treatment.getDescription();
        this.period_start = dateTimeFormat(treatment.getPeriod_start());
        this.period_end = dateTimeFormat(treatment.getPeriod_end());
        this.dose = treatment.getDose();
        this.status = treatment.getStatus();
        this.treatmentResult = treatment.getTreatmentResult();
        this.patient = treatment.getPatient();
        this.remedy = treatment.getRemedy();
        this.timePattern = treatment.getTimePattern();
    }

    public int getTreatmentId() { return treatmentId; }
    public void setTreatmentId(int treatmentId) { this.treatmentId = treatmentId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPeriod_start() { return period_start; }
    public void setPeriod_start(String period_start) { this.period_start = period_start; }

    public String getPeriod_end() { return period_end; }
    public void setPeriod_end(String period_end) { this.period_end = period_end; }

    public int getDose() { return dose; }
    public void setDose(int dose) { this.dose = dose; }

    public TreatmentStatus getStatus() { return status; }
    public void setStatus(TreatmentStatus status) { this.status = status; }

    public String getTreatmentResult() { return treatmentResult; }
    public void setTreatmentResult(String treatmentResult) { this.treatmentResult = treatmentResult; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Remedy getRemedy() { return remedy; }
    public void setRemedy(Remedy remedy) { this.remedy = remedy; }

    public Pattern getTimePattern() { return timePattern; }
    public void setTimePattern(Pattern timePattern) { this.timePattern = timePattern; }

    public String dateTimeFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}
