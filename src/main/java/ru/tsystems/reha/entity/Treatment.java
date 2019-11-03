package ru.tsystems.reha.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "treatment")
@NamedQueries({
        @NamedQuery(name = "Treatment.findByPatient", query = "select tr from Treatment tr " +
                "where tr.patient.patientId = :patientId")})
public class Treatment {
    public Treatment(){}

    //---------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_id")
    private int treatmentId;
    public int getTreatmentId() {
        return treatmentId;
    }
    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    //---------------------------------------------------------
    @Column(name = "description")
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //---------------------------------------------------------
    @Column(name = "period_start")
    private Date period_start;
    public Date getPeriod_start() {
        return period_start;
    }
    public void setPeriod_start(Date period_start) {
        this.period_start = period_start;
    }

    //---------------------------------------------------------
    @Column(name = "period_end")
    private Date period_end;
    public Date getPeriod_end() {
        return period_end;
    }
    public void setPeriod_end(Date period_end) {
        this.period_end = period_end;
    }

    //---------------------------------------------------------
    @Column(name = "dose")
    private int dose;
    public int getDose() {
        return dose;
    }
    public void setDose(int dose) {
        this.dose = dose;
    }

    //---------------------------------------------------------
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TreatmentStatus status;
    public TreatmentStatus getStatus() {
        return status;
    }
    public void setStatus(TreatmentStatus status) {
        this.status = status;
    }

    //---------------------------------------------------------
    @Column(name = "treatment_result")
    private String treatmentResult;
    public String getTreatmentResult() {
        return treatmentResult;
    }
    public void setTreatmentResult(String treatmentResult) {
        this.treatmentResult = treatmentResult;
    }
    //---------------------------------------------------------

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
    private Patient patient;
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    @ManyToOne
    @JoinColumn(name = "remedy_id", referencedColumnName = "remedy_id", nullable = false)
    private Remedy remedy;
    public Remedy getRemedy() { return remedy; }
    public void setRemedy(Remedy remedy) { this.remedy = remedy; }

    @ManyToOne
    @JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id", nullable = false)
    private Pattern timePattern;
    public Pattern getTimePattern() { return timePattern; }
    public void setTimePattern(Pattern timePattern) { this.timePattern = timePattern; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Treatment treatment = (Treatment) o;

        if (treatmentId != treatment.treatmentId) return false;
        if (dose != treatment.dose) return false;
        if (description != null ? !description.equals(treatment.description) : treatment.description != null)
            return false;
        if (period_start != null ? !period_start.equals(treatment.period_start) : treatment.period_start != null) return false;
        if (period_end != null ? !period_end.equals(treatment.period_end) : treatment.period_end != null) return false;
        if (status != null ? !status.equals(treatment.status) : treatment.status != null) return false;
        if (treatmentResult != null ? !treatmentResult.equals(treatment.treatmentResult) : treatment.treatmentResult != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = treatmentId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (period_start != null ? period_start.hashCode() : 0);
        result = 31 * result + (period_end != null ? period_end.hashCode() : 0);
        result = 31 * result + dose;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (treatmentResult != null ? treatmentResult.hashCode() : 0);
        return result;
    }

//    @ManyToOne
//    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
//    public Patient getPatientByPatientId() {
//        return patientByPatientId;
//    }
//
//    public void setPatientByPatientId(Patient patientByPatientId) {
//        this.patientByPatientId = patientByPatientId;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "remedy_id", referencedColumnName = "remedy_id", nullable = false)
//    public Remedy getRemedyByRemedyId() {
//        return remedyByRemedyId;
//    }
//
//    public void setRemedyByRemedyId(Remedy remedyByRemedyId) {
//        this.remedyByRemedyId = remedyByRemedyId;
//    }
}
