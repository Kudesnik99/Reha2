package ru.tsystems.reha.converters;

import ru.tsystems.reha.dto.TreatmentDto;
import ru.tsystems.reha.entity.Treatment;

public class TreatmentConverter {

    private TreatmentConverter() {
        // util class
    }

    public static Treatment toTreatment(TreatmentDto treatmentDto) {
        Treatment result = new Treatment();
        result.setTreatmentId(treatmentDto.getTreatmentId());
        result.setTimePattern(PatternConverter.toPattern(treatmentDto.getTimePattern()));
        result.setDescription(treatmentDto.getDescription());
        result.setPeriodStart(treatmentDto.getPeriodStart());
        result.setPeriodEnd(treatmentDto.getPeriodEnd());
        result.setDose(treatmentDto.getDose());
        result.setStatus(treatmentDto.getStatus());
        result.setTreatmentResult(treatmentDto.getTreatmentResult());
        result.setPatient(PatientConverter.toPatient(treatmentDto.getPatient()));
        result.setRemedy(RemedyConverter.toRemedy(treatmentDto.getRemedy()));
        return result;
    }

    public static TreatmentDto toTreatmentDto(Treatment treatment) {
        TreatmentDto result = new TreatmentDto();
        result.setTreatmentId(treatment.getTreatmentId());
        result.setTimePattern(PatternConverter.toPatternDto(treatment.getTimePattern()));
        result.setDescription(treatment.getDescription());
        result.setPeriodStart(treatment.getPeriodStart());
        result.setPeriodEnd(treatment.getPeriodEnd());
        result.setDose(treatment.getDose());
        result.setStatus(treatment.getStatus());
        result.setTreatmentResult(treatment.getTreatmentResult());
        result.setPatient(PatientConverter.toPatientDto(treatment.getPatient()));
        result.setPatientId(treatment.getPatient().getPatientId());
        result.setRemedy(RemedyConverter.toRemedyDto(treatment.getRemedy()));
        result.setRemedyId(treatment.getRemedy().getRemedyId());
        return result;
    }
}
