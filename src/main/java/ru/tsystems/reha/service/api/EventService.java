package ru.tsystems.reha.service.api;

import ru.tsystems.reha.dto.EventDto;
import ru.tsystems.reha.dto.PatientDto;
import ru.tsystems.reha.rest.model.Events;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;

public interface EventService {

    List<EventDto> getEvents() throws ServiceException;

    List<EventDto> getEventsByTreatmentId(Long treatmentId) throws ServiceException;

    PatientDto getPatientByTreatmentId(Long treatmentId) throws ServiceException;

    EventDto getEvent(Long id) throws ServiceException;

    void saveEvent(EventDto eventDto) throws ServiceException;

    List<EventDto> getEventsByTreatmentIdToday(Long treatmentId) throws ServiceException;

    List<EventDto> getEventsByTreatmentIdThisHour(Long treatmentId) throws ServiceException;

    List<EventDto> getEventsToday() throws ServiceException;

    List<EventDto> getEventsThisHour() throws ServiceException;

    Events getAllEventsForPanel() throws ServiceException;

    Events getSomeEventsForPanel(List<Long> ids) throws ServiceException;
}
