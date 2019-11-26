package ru.tsystems.reha.service.api;

import ru.tsystems.reha.dto.TreatmentDto;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.enums.TreatmentStatus;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;

public interface TreatmentService {

    List<TreatmentDto> getTreatments() throws ServiceException;

    void saveTreatment(TreatmentDto treatmentDto) throws ServiceException;

    TreatmentDto getTreatment(Long id) throws ServiceException;

    void deleteTreatment(Long id) throws ServiceException;

    Patient getPatientByPatientId(Long id) throws ServiceException;

    List<TreatmentDto> getTreatmentsByPatientId(Long id) throws ServiceException;

    void generateEvents(Long id) throws ServiceException;

    void updatePatientDischargedStatus(Patient patient) throws ServiceException;

    List<TreatmentStatus> getTreatmentStatusList(TreatmentStatus currentStatus);

}