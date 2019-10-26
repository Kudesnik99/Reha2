package ru.tsystems.reha.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.dao.DaoException;
import ru.tsystems.reha.dao.PatientDao;
import ru.tsystems.reha.dao.UserDao;
import ru.tsystems.reha.dao.UserDaoImpl;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.User;
import ru.tsystems.reha.model.PatientForm;
import ru.tsystems.reha.model.PatientFormConverter;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger LOG = Logger.getLogger(TreatmentServiceImpl.class);

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public List<Patient> getPatients() throws ServiceException {
        try {
            return patientDao.findAll();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void savePatient(PatientForm thePatient, String doctorEmail) throws ServiceException {
        try {
            Patient patient = PatientFormConverter.toPatient(thePatient);
            patient.setDoctor(userDao.findByEmail(doctorEmail));
            patientDao.saveOrUpdate(patient);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public Patient getPatient(int theId) throws ServiceException {
        try {
            return patientDao.findById(theId);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void deletePatient(int theId) throws ServiceException {
        try {
            patientDao.remove(patientDao.findById(theId));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }

    }
}
