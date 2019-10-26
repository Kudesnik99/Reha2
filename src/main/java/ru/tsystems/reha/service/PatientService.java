package ru.tsystems.reha.service;

import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.model.PatientForm;

import java.util.List;

public interface PatientService {

    List<Patient> getPatients() throws ServiceException;

    void savePatient(PatientForm thePatient, String doctorEmail) throws ServiceException;

    Patient getPatient(int theId) throws ServiceException;

    void deletePatient(int theId)  throws ServiceException;

}
