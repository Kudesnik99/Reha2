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
        result.setDescription(treatmentDto.getDescription());
        result.setPeriodStart(treatmentDto.getPeriodStart());
        result.setPeriodEnd(treatmentDto.getPeriodEnd());
        result.setDose(treatmentDto.getDose());
        result.setStatus(treatmentDto.getStatus());
        result.setTreatmentResult(treatmentDto.getTreatmentResult());
        return result;
    }

    public static TreatmentDto toTreatmentDto(Treatment treatment) {
        TreatmentDto result = new TreatmentDto();
        result.setTreatmentId(treatment.getTreatmentId());
        result.setDescription(treatment.getDescription());
        result.setPeriodStart(treatment.getPeriodStart());
        result.setPeriodEnd(treatment.getPeriodEnd());
        result.setDose(treatment.getDose());
        result.setStatus(treatment.getStatus());
        result.setTreatmentResult(treatment.getTreatmentResult());

        result.setPatientDto(PatientConverter.toPatientDto(treatment.getPatient()));
        result.setRemedyDto(RemedyConverter.toRemedyDto(treatment.getRemedy()));
        result.setPatternDto(PatternConverter.toPatternDto(treatment.getTimePattern()));
        return result;
    }
}
