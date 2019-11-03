package ru.tsystems.reha.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.converters.PatientConverter;
import ru.tsystems.reha.dao.api.PatientDao;
import ru.tsystems.reha.dao.api.UserDao;
import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.dto.PatientDto;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.service.api.PatientService;
import ru.tsystems.reha.service.exception.ServiceError;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger LOG = Logger.getLogger(PatientServiceImpl.class);

    private final PatientDao patientDao;

    private final UserDao userDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao, UserDao userDao) {
        this.patientDao = patientDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<PatientDto> getPatients() throws ServiceException {
        try {
            return patientDao.findAll().stream().map(PatientConverter::toPatientDto).collect(Collectors.toList());
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void savePatient(PatientDto patientDto, String doctorEmail) throws ServiceException {
        try {
            Patient patient = PatientConverter.toPatient(patientDto);
            patient.setDoctor(userDao.findByEmail(doctorEmail));
            patientDao.saveOrUpdate(patient);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void updatePatient(PatientDto patientDto, String doctorEmail) throws ServiceException {
        try {
            Patient patient = patientDao.findById(patientDto.getPatientId());
            PatientConverter.updatePatient(patient, patientDto);
            patientDao.saveOrUpdate(patient);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public PatientDto getPatient(Long id) throws ServiceException {
        try {
            return PatientConverter.toPatientDto(patientDao.findById(id));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void deletePatient(Long id) throws ServiceException {
        try {
            patientDao.remove(patientDao.findById(id));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }

    }
}
