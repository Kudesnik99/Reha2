package ru.tsystems.reha.service.api;

import ru.tsystems.reha.dto.EventDto;
import ru.tsystems.reha.dto.PatientDto;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;

public interface EventService {

    List<EventDto> getEvents() throws ServiceException;

    List<EventDto> getEventsByTreatmentId(Long treatmentId) throws ServiceException;

    PatientDto getPatientByTreatmentId(Long treatmentId) throws ServiceException;
}
