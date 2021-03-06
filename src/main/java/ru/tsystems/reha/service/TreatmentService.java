package ru.tsystems.reha.service;

import ru.tsystems.reha.entity.Event;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.model.TreatmentForm;

import java.util.List;

public interface TreatmentService {

    public List<Treatment> getTreatments() throws ServiceException;

    public void saveTreatment(TreatmentForm theTreatment) throws ServiceException;

    public Treatment getTreatment(int theId) throws ServiceException;

    public void deleteTreatment(int theId) throws ServiceException;

    public Patient getPatientByPatientId(int theId) throws ServiceException;

    public List<Treatment> getTreatmentsByPatientId(int theId) throws ServiceException;

    public List<Event> generateEvents (int theId) throws ServiceException;

}