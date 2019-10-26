package ru.tsystems.reha.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.dao.DaoException;
import ru.tsystems.reha.dao.PatientDao;
import ru.tsystems.reha.dao.TreatmentDao;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.model.PatientFormConverter;
import ru.tsystems.reha.model.TreatmentForm;
import ru.tsystems.reha.model.TreatmentFormConverter;

import java.util.List;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private static final Logger LOG = Logger.getLogger(TreatmentServiceImpl.class);

    @Autowired
    private TreatmentDao treatmentDao;

    @Autowired
    private PatientDao patientDao;

    @Override
    @Transactional
    public List<Treatment> getTreatments() throws ServiceException {
        try {
            return treatmentDao.findAll(); //getTreatments();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void saveTreatment(TreatmentForm theTreatment) throws ServiceException {
        try {

            Patient patient = patientDao.findById(theTreatment.getPatientId());
//            patient.setDoctor(userDao.findByEmail(doctorEmail));
//            patientDao.saveOrUpdate(patient);

            theTreatment.setPatient(patient);
            Treatment treatment = TreatmentFormConverter.toTreatment(theTreatment);
            //treatment.setPatient(patientDao.findById());
            treatmentDao.saveOrUpdate(treatment);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }


    @Override
    @Transactional
    public Treatment getTreatment(int theId) {
        return treatmentDao.getTreatment(theId);
    }

    @Override
    @Transactional
    public void deleteTreatment(int theId) {
        treatmentDao.deleteTreatment(theId);
    }

    @Override
    @Transactional
    public Patient getPatientByPatientId(int theId) throws ServiceException {
        try {
            return patientDao.findById(theId);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    public List<Treatment> getTreatmentsByPatientId(int theId) throws ServiceException {
        try {
            //return patientDao.findById(theId);
            return treatmentDao.findByPatientId(theId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }
}
