package ru.tsystems.reha.service;

import ru.tsystems.reha.entity.Event;
import ru.tsystems.reha.entity.Patient;

import java.util.List;

public interface EventService {

    List<Event> getEvents() throws ServiceException;

    List<Event> getEventsByTreatmentId(int treatmentId) throws ServiceException;

    Patient getPatientByTreatmentId(int treatmentId) throws ServiceException;

    void saveEvents(Event event) throws ServiceException;
}
