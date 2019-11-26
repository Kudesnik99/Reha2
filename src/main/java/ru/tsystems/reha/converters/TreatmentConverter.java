package ru.tsystems.reha.converters;

import ru.tsystems.reha.dto.TreatmentDto;
import ru.tsystems.reha.entity.Treatment;

public class TreatmentConverter {

    private TreatmentConverter() {
        // util class
    }

    public static Treatment toTreatment(TreatmentDto treatmentDto) {
        Treatment result = new Treatment();
        updateTreatment(result, treatmentDto);
        return result;
    }

    public static TreatmentDto toTreatmentDto(Treatment treatment) {
        TreatmentDto result = new TreatmentDto();
        updateTreatmentDto(result, treatment);
        return result;
    }

    public static void updateTreatmentDto(TreatmentDto treatmentDto, Treatment treatment) {
        treatmentDto.setTreatmentId(treatment.getTreatmentId());
        treatmentDto.setDescription(treatment.getDescription());
        treatmentDto.setPeriodStart(treatment.getPeriodStart());
        treatmentDto.setPeriodEnd(treatment.getPeriodEnd());
        treatmentDto.setDose(treatment.getDose());
        treatmentDto.setStatus(treatment.getStatus());
        treatmentDto.setTreatmentResult(treatment.getTreatmentResult());
        treatmentDto.setPatientDto(PatientConverter.toPatientDto(treatment.getPatient()));
        treatmentDto.setRemedyDto(RemedyConverter.toRemedyDto(treatment.getRemedy()));
        treatmentDto.setPatternDto(PatternConverter.toPatternDto(treatment.getTimePattern()));
    }

    public static void updateTreatment(Treatment treatment, TreatmentDto treatmentDto) {
        treatment.setTreatmentId(treatmentDto.getTreatmentId());
        treatment.setDescription(treatmentDto.getDescription());
        treatment.setPeriodStart(treatmentDto.getPeriodStart());
        treatment.setPeriodEnd(treatmentDto.getPeriodEnd());
        treatment.setDose(treatmentDto.getDose());
        treatment.setStatus(treatmentDto.getStatus());
        treatment.setTreatmentResult(treatmentDto.getTreatmentResult());
    }
}
