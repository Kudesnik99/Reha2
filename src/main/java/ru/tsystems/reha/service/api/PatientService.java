package ru.tsystems.reha.service.api;

import ru.tsystems.reha.dto.PatientDto;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;

public interface PatientService {

    List<PatientDto> getPatients() throws ServiceException;

    void savePatient(PatientDto patientDto, String doctorEmail) throws ServiceException;

    void updatePatient(PatientDto patientDto, String doctorEmail) throws ServiceException;

    PatientDto getPatient(Long id) throws ServiceException;

    void deletePatient(Long id)  throws ServiceException;

}
