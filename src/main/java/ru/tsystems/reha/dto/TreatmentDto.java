package ru.tsystems.reha.dto;

import org.springframework.format.annotation.DateTimeFormat;
import ru.tsystems.reha.entity.enums.TreatmentStatus;
import ru.tsystems.reha.util.DataRange;
import ru.tsystems.reha.util.FromToday;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;

@DataRange
public class TreatmentDto {

    private Long treatmentId;

    private String description;

    @FromToday
    @DateTimeFormat(pattern="dd.MM.yyyy")
    @NotNull
    private Date periodStart;

    @FutureOrPresent
    @DateTimeFormat(pattern="dd.MM.yyyy")
    @NotNull
    private Date periodEnd;

    private Integer dose;

    private TreatmentStatus status = TreatmentStatus.PLANNED;

    private String treatmentResult;

    private PatientDto patientDto;

    private RemedyDto remedyDto;

    private PatternDto patternDto;

    public Long getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
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

    public PatientDto getPatientDto() { return patientDto; }

    public void setPatientDto(PatientDto patientDto) { this.patientDto = patientDto; }

    public RemedyDto getRemedyDto() {
        return remedyDto;
    }

    public void setRemedyDto(RemedyDto remedyDto) {
        this.remedyDto = remedyDto;
    }

    public PatternDto getPatternDto() {
        return patternDto;
    }

    public void setPatternDto(PatternDto patternDto) {
        this.patternDto = patternDto;
    }
}
